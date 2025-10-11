package br.com.tads.polia.poliaappsrv.infrastructure.config;



import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
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
                service.register(new Admin(
                        "2895e932-b438-4047-b140-b57c3177b33c",
                        "Admin",
                        "polai@gmail.com",
                    "12345678901",
                    "11999999999",
                    "1234"
                ));
                System.out.println(">> Funcionário inicial inserido");
            }
        };
    }
}