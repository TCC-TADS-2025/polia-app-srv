package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
