package com.ceiba.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ManejadorExcepciones extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccionNoPermitidaException.class)
    public final ResponseEntity<DetalleError> manejadorAccionNoPermitida(AccionNoPermitidaException ex) {
        DetalleError detalleError = new DetalleError(ex.getMessage());
        return new ResponseEntity<DetalleError>(detalleError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TipoUsuarioNoPermitidoException.class)
    public final ResponseEntity<DetalleError> manejadorTipoUsuarioNoPermitido(TipoUsuarioNoPermitidoException ex) {
        DetalleError detalleError = new DetalleError(ex.getMessage());
        return new ResponseEntity<DetalleError>(detalleError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrestamoNoEncontradoException.class)
    public final ResponseEntity<DetalleError> manejadorPrestamoNoEncontrado(PrestamoNoEncontradoException ex) {
        DetalleError detalleError = new DetalleError(ex.getMessage());
        return new ResponseEntity<DetalleError>(detalleError, HttpStatus.NOT_FOUND);
    }

}