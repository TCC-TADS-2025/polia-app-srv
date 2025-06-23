package br.com.tads.polia.poliaappsrv.infrastructure.config;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;


@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedAdmin(AuthUseCase service) {
        return args -> {
            if (service.getAll().isEmpty()) {
                service.register(new RegisterDTO(
                    "polai@gmail.com",
                    "Admin",
                    "1234",
                    "12345678901",
                    "11999999999",
                    Role.ADMIN
                ));
                System.out.println(">> Funcionário inicial inserido");
            }
        };
    }
}