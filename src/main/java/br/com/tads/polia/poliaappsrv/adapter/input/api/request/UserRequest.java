package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String cpf;
    private String phone;
}
