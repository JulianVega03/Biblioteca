package com.ceiba.biblioteca.model;

import com.ceiba.biblioteca.exceptions.TipoUsuarioNoPermitidoException;

import java.util.Arrays;

public enum TipoUsuario {

    AFILIADO(1),
    EMPLEADO(2),
    INVITADO(3);

    private final int idTipoUsuario;

    TipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public static TipoUsuario valueOf(Integer idTipoUsuario) throws TipoUsuarioNoPermitidoException{
        return Arrays.stream(TipoUsuario.values())
                .filter(value -> value.getIdTipoUsuario().equals(idTipoUsuario))
                .findAny()
                .orElseThrow(() -> new TipoUsuarioNoPermitidoException("Tipo de usuario no permitido en la biblioteca"));
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }
}
