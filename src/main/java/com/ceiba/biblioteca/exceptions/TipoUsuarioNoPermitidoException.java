package com.ceiba.biblioteca.exceptions;

public class TipoUsuarioNoPermitidoException extends RuntimeException {
    public TipoUsuarioNoPermitidoException(String mensaje) {
        super(mensaje);
    }
}
