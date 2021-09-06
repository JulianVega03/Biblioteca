package com.ceiba.biblioteca.mapper;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoCreadoResponse;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.model.TipoUsuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PrestamoMapperImpl implements PrestamoMapper {

    @Override
    public Prestamo map(PrestamoRequest prestamoRequest, LocalDate fechaDevolucion) {
        return new Prestamo(
                prestamoRequest.getIsbn(),
                prestamoRequest.getIdentificacionUsuario(),
                TipoUsuario.valueOf(prestamoRequest.getTipoUsuario()),
                fechaDevolucion
        );
    }

    @Override
    public PrestamoCreadoResponse mapToPrestamoCreadoResponse(Prestamo prestamo) {
        return new PrestamoCreadoResponse(prestamo.getId(), prestamo.getFechaDevolucion());
    }

    @Override
    public PrestamoResponse mapToPrestamoResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getIsbn(),
                prestamo.getIdUsuario(),
                prestamo.getTipoUsuario().getIdTipoUsuario(),
                prestamo.getFechaDevolucion()
        );
    }

}
