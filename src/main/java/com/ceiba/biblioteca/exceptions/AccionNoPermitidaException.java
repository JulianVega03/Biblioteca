package com.ceiba.biblioteca.exceptions;

public class AccionNoPermitidaException extends RuntimeException {
    public AccionNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}