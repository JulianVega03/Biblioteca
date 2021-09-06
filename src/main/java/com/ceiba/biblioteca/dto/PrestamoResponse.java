package com.ceiba.biblioteca.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrestamoResponse {

    private long id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private LocalDate fechaMaximaDevolucion;

    public PrestamoResponse(long id, String isbn, String identificacionUsuario, int tipoUsuario, LocalDate fechaMaximaDevolucion) {
        this.id = id;
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public long getId() {
        return id;
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

    public String getFechaMaximaDevolucion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaMaximaDevolucion.format(formatter);
    }

}
