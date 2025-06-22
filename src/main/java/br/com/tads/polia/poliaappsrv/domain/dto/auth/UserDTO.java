package br.com.tads.polia.poliaappsrv.domain.dto.auth;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String cpf;
    private String phone;
    private Role role;
    @JsonIgnore
    private String password;
    @Builder.Default
    private List<String> authorities = new ArrayList<>();
}