package br.com.joao.library.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String msg) {
        super(msg);
    }

}
