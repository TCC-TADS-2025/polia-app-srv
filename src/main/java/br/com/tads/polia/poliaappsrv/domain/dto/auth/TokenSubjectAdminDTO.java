package br.com.tads.polia.poliaappsrv.domain.dto.auth;


import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenSubjectAdminDTO {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private long expiresIn;
    private Admin admin;
}
