package br.com.tads.polia.poliaappsrv.infrastructure.security;

import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AdminRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email)
                .map(admin -> (UserDetails) admin)
                .orElseGet(() -> userRepository.findByEmail(email)
                        .map(user -> (UserDetails) user)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email)));
    }

    public UserDetails loadUserById(String userId) throws UsernameNotFoundException {
        return adminRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com id: " + userId));
    }
}