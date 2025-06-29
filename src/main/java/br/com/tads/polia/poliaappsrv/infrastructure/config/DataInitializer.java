package br.com.tads.polia.poliaappsrv.infrastructure.config;



import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
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
                service.register(new AdminRequest(
                        "Admin",
                        "polai@gmail.com",
                    "12345678901",
                    "11999999999",
                    "1234",
                    Role.ADMIN
                ));
                System.out.println(">> Funcionário inicial inserido");
            }
        };
    }
}