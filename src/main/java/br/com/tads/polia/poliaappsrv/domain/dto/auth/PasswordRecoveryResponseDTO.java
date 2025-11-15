package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordRecoveryResponseDTO {

    private String message;
    private String email;
}
