package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateRequest implements Serializable {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String password;
    private String confirmPassword;
}
