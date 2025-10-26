package br.com.tads.polia.poliaappsrv.domain.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Email não encontrado: " + email);
    }
    
}
