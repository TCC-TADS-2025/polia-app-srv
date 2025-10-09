package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.LoginDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectAdminDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import br.com.tads.polia.poliaappsrv.domain.exception.CpfAlredyExistsException;
import br.com.tads.polia.poliaappsrv.domain.exception.EmailAlredyExistsException;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.UserMapper;
import br.com.tads.polia.poliaappsrv.infrastructure.security.JwtTokenProvider;
import br.com.tads.polia.poliaappsrv.port.output.IAdminOutputPort;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthUseCase {
    
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final IAdminOutputPort adminOutputPort;

    private final UserMapper userMapper;

    public TokenSubjectDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + loginDTO.getEmail()));
        
        UserDTO userDTO = userMapper.toDTO(user);
        String jwt = jwtTokenProvider.generateToken(userDTO);

        return TokenSubjectDTO.builder()
            .accessToken(jwt)
            .user(userDTO)
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

    public TokenSubjectDTO registerUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlredyExistsException();
        }

        UserEntity user = userMapper.fromRegisterUserRequest(userRequest);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.USER);

        user = userRepository.save(user);

        String jwt = jwtTokenProvider.generateToken(userMapper.toDTO(user));

        return TokenSubjectDTO.builder()
                .accessToken(jwt)
                .user(userMapper.toDTO(user))
                .build();
    }


    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
