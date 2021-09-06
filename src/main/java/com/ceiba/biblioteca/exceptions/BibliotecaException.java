package com.ceiba.biblioteca.exceptions;

public class BibliotecaException extends RuntimeException {

    public BibliotecaException(String mensaje, Exception excepcion) {
        super(mensaje, excepcion);
    }
    public BibliotecaException(String mensaje) {
        super(mensaje);
    }
    
}
