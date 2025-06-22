package br.com.tads.polia.poliaappsrv.domain.usecase;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tads.polia.poliaappsrv.adapter.output.repositories.UserRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.LoginDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.UserMapper;
import br.com.tads.polia.poliaappsrv.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUseCase {
    
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserMapper userMapper;

    public TokenSubjectDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(loginDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + loginDTO.getEmail()));
        
        UserDTO userDTO = userMapper.toDTO(user);
        String jwt = jwtTokenProvider.generateToken(userDTO);

        return TokenSubjectDTO.builder()
            .accessToken(jwt)
            .user(userDTO)
            .build();
    }

    public TokenSubjectDTO register(RegisterDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = userMapper.fromRegister(userDTO);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setEnabled(true);

        user = userRepository.save(user);

        String jwt = jwtTokenProvider.generateToken(userMapper.toDTO(user));

        return TokenSubjectDTO.builder()
            .accessToken(jwt)
            .user(userMapper.toDTO(user))
            .build();
    }
}
