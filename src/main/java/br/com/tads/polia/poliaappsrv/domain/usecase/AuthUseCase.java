package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.LoginDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectAdminDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.exception.CpfAlredyExistsException;
import br.com.tads.polia.poliaappsrv.domain.exception.EmailAlredyExistsException;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.AdminMapper;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.UserMapper;
import br.com.tads.polia.poliaappsrv.infrastructure.security.JwtTokenProvider;
import br.com.tads.polia.poliaappsrv.port.output.IAdminOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.IUserOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AdminRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUseCase {
    
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final IAdminOutputPort adminOutputPort;
    private final IUserOutputPort userOutputPort;

    private final AdminMapper adminMapper;
    private final UserMapper userMapper;

    public TokenSubjectAdminDTO loginAdmin(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AdminEntity adminEntity = adminRepository.findByEmail(loginDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + loginDTO.getEmail()));
        
        Admin admin = adminMapper.adminEntityToAdmin(adminEntity);
        String jwt = jwtTokenProvider.generateTokenAdmin(admin);

        return TokenSubjectAdminDTO.builder()
            .accessToken(jwt)
            .admin(admin)
            .build();
    }

    public TokenSubjectDTO loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity userEntity = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + loginDTO.getEmail()));

        User user = userMapper.userEntityToUser(userEntity);
        String jwt = jwtTokenProvider.generateToken(user);

        return TokenSubjectDTO.builder()
                .accessToken(jwt)
                .user(user)
                .build();
    }

    public TokenSubjectAdminDTO register(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new EmailAlredyExistsException();
        }
        if (adminRepository.existsByCpf(admin.getCpf())) {
            throw new CpfAlredyExistsException();
        }

        admin = adminOutputPort.createAdmin(admin);

        String jwt = jwtTokenProvider.generateTokenAdmin(admin);

        return TokenSubjectAdminDTO.builder()
            .accessToken(jwt)
            .admin(admin)
            .build();
    }

    public TokenSubjectDTO registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlredyExistsException();
        }
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new CpfAlredyExistsException();
        }
        user.setRole(null);
        user = userOutputPort.createUser(user);

        String jwt = jwtTokenProvider.generateToken(user);

        return TokenSubjectDTO.builder()
                .accessToken(jwt)
                .user(user)
                .build();
    }


    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
