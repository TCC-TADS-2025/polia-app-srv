package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenSubjectDTO {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private UserDTO user;
}
