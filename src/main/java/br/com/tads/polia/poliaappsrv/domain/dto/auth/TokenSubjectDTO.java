package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import java.time.LocalDateTime;

import br.com.tads.polia.poliaappsrv.domain.entity.User;
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
    private long expiresIn;
    private User user;
}
