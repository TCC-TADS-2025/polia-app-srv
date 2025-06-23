package br.com.tads.polia.poliaappsrv.domain.exception;

public class CpfAlredyExistsException extends RuntimeException {

    public CpfAlredyExistsException(String cpf) {
        super("CPF já cadastrado na base de dados");
    }

    public CpfAlredyExistsException() {
        super("CPF já cadastrado na base de dados");
    }
    
}
