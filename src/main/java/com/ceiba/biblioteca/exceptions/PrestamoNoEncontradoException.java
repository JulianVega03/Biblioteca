package com.ceiba.biblioteca.exceptions;

public class PrestamoNoEncontradoException extends RuntimeException{
    public PrestamoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
