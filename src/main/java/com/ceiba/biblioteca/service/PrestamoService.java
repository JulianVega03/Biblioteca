package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoCreadoResponse;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.exceptions.AccionNoPermitidaException;
import com.ceiba.biblioteca.exceptions.PrestamoNoEncontradoException;
import com.ceiba.biblioteca.exceptions.TipoUsuarioNoPermitidoException;

public interface PrestamoService {

    PrestamoCreadoResponse realizarPrestamo(PrestamoRequest prestamoRequest)
            throws AccionNoPermitidaException, TipoUsuarioNoPermitidoException;
    PrestamoResponse consultarPrestamo(long id) throws PrestamoNoEncontradoException;
}
