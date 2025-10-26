package br.com.tads.polia.poliaappsrv.domain.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private String id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String password;
    private Role role = Role.ADMIN;
}
