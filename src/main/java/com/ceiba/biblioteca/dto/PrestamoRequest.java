package com.ceiba.biblioteca.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PrestamoRequest {

    @NotBlank(message = "Isbn es requerido")
    @Size(max = 10, message = "ISBN debe contener maximo 10 caracteres")
    private String isbn;

    @NotBlank(message = "Identificador de Usuario es requerido")
    @Size(max = 10, message = "Identificador de Usuario debe contener maximo 10 caracteres")
    private String identificacionUsuario;

    @NotNull(message = "Tipo de Usuario es requerido")
    private int tipoUsuario;

    public PrestamoRequest(
            @NotBlank(message = "Isbn es requerido")
            @Size(max = 10, message = "ISBN debe contener maximo 10 caracteres") String isbn,
            @NotBlank(message = "Identificador de Usuario es requerido")
            @Size(max = 10, message = "Identificador de Usuario debe contener maximo 10 caracteres") String identificacionUsuario,
            @NotNull(message = "Tipo de Usuario es requerido") int tipoUsuario
    ) {
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }
}
