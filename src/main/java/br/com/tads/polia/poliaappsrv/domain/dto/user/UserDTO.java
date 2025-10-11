package br.com.tads.polia.poliaappsrv.domain.dto.user;

import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    private String password;
    private Role role;
    @Builder.Default
    private List<String> authorities = new ArrayList<>();
}