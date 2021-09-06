package com.ceiba.biblioteca.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrestamoCreadoResponse {

    private long id;

    private LocalDate fechaMaximaDevolucion;

    public PrestamoCreadoResponse(long id, LocalDate fechaMaximaDevolucion) {
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public long getId() {
        return id;
    }

    public String getFechaMaximaDevolucion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaMaximaDevolucion.format(formatter);
    }

}
