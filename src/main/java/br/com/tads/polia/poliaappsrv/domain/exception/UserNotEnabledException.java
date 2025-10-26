package br.com.tads.polia.poliaappsrv.domain.exception;

public class UserNotEnabledException extends RuntimeException {
    public UserNotEnabledException(String email) {
        super("Usuário não está habilitado: " + email);
    }
}
