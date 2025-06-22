package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}
