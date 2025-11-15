package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String name;
    private String email;
    private String cpf;
}
