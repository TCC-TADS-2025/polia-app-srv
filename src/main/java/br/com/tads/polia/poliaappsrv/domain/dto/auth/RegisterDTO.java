package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;

public record RegisterDTO(
    String email,
    String name,
    String password,
    String cpf,
    String phone,
    Role role
) {
    
}
