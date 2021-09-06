package com.ceiba.biblioteca.mapper;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoCreadoResponse;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.model.Prestamo;

import java.time.LocalDate;

public interface PrestamoMapper {

      Prestamo map(PrestamoRequest prestamoRequest, LocalDate fechaDevolucion);
      PrestamoCreadoResponse mapToPrestamoCreadoResponse(Prestamo prestamo);
      PrestamoResponse mapToPrestamoResponse(Prestamo prestamo);
}
