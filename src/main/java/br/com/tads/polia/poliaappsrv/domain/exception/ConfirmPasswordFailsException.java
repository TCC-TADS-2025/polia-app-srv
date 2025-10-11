package br.com.tads.polia.poliaappsrv.domain.exception;

public class ConfirmPasswordFailsException extends RuntimeException {

    public ConfirmPasswordFailsException(String cpf) {
        super("As senhas não conferem");
    }

    public ConfirmPasswordFailsException() {
        super("As senhas não conferem");
    }
}
