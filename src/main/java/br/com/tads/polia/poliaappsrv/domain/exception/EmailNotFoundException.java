package br.com.tads.polia.poliaappsrv.domain.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
    
}
