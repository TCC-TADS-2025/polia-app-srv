package br.com.tads.polia.poliaappsrv.domain.exception;

public class EmailAlredyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlredyExistsException() {
        super("Email já cadastrado na base dados: ");
    }
    
}
