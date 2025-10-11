package br.com.tads.polia.poliaappsrv.adapter.input.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String cpf;
}
