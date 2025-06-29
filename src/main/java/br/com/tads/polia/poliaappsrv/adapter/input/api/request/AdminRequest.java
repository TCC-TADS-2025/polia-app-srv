package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest implements Serializable {
    private static final long serialVersionUID = 1L;

        private String name;
        private String email;
        private String cpf;
        private String phone;
        private String password;
        private Role role;
}
