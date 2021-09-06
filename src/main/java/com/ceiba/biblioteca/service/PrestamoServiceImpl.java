package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoCreadoResponse;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.exceptions.AccionNoPermitidaException;
import com.ceiba.biblioteca.exceptions.PrestamoNoEncontradoException;
import com.ceiba.biblioteca.exceptions.TipoUsuarioNoPermitidoException;
import com.ceiba.biblioteca.mapper.PrestamoMapper;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.model.TipoUsuario;
import com.ceiba.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class PrestamoServiceImpl implements PrestamoService{

    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, PrestamoMapper prestamoMapper){
        this.prestamoRepository = prestamoRepository;
        this.prestamoMapper = prestamoMapper;
    }

    @Override
    public PrestamoCreadoResponse realizarPrestamo(PrestamoRequest prestamoRequest) throws AccionNoPermitidaException, TipoUsuarioNoPermitidoException {

        String idUsuario = prestamoRequest.getIdentificacionUsuario();

        TipoUsuario tipoUsuario;

        try {
            tipoUsuario = TipoUsuario.valueOf(prestamoRequest.getTipoUsuario());
        }catch (TipoUsuarioNoPermitidoException e){
            throw new TipoUsuarioNoPermitidoException(e.getMessage());
        }

        LocalDate fechaDevolucion;

        if(tipoUsuario == TipoUsuario.INVITADO){

            if(!tieneUnPrestamo(idUsuario)){
                fechaDevolucion = calcularFechaDevolucionParaTipoUsuario(TipoUsuario.INVITADO);
            }else{
                String mensajeError =
                        String.format(
                                "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede "
                                        + "realizar otro préstamo", idUsuario
                        );
                throw new AccionNoPermitidaException(mensajeError);
            }
        }else if(tipoUsuario == TipoUsuario.EMPLEADO){
            fechaDevolucion = calcularFechaDevolucionParaTipoUsuario(TipoUsuario.EMPLEADO);

        }else{
            fechaDevolucion = calcularFechaDevolucionParaTipoUsuario(TipoUsuario.AFILIADO);
        }

        Prestamo nuevoPrestamo = prestamoMapper.map(prestamoRequest, fechaDevolucion);
        Prestamo prestamoCreado = prestamoRepository.save(nuevoPrestamo);
        return prestamoMapper.mapToPrestamoCreadoResponse(prestamoCreado);
    }

    public PrestamoResponse consultarPrestamo(long id) throws PrestamoNoEncontradoException{
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(
                        ()-> new PrestamoNoEncontradoException(String.format("El Prestamo con id %x no existe", id))
                );
        return prestamoMapper.mapToPrestamoResponse(prestamo);
    }

    private boolean tieneUnPrestamo(String idUsuario){
        return prestamoRepository.existsByIdUsuario(idUsuario);
    }

    private LocalDate calcularFechaDevolucionParaTipoUsuario(TipoUsuario tipoUsuario){
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion;

        if(tipoUsuario == TipoUsuario.AFILIADO){
            fechaDevolucion = fechaPrestamo.plusDays(10L);
        }else if(tipoUsuario == TipoUsuario.EMPLEADO){
            fechaDevolucion = fechaPrestamo.plusDays(8L);
        }else{
            fechaDevolucion = fechaPrestamo.plusDays(7L);
        }

        long cantidadFinesDeSemana = contarFinesDeSemana(fechaPrestamo, fechaDevolucion);
        fechaDevolucion = fechaDevolucion.plusDays(cantidadFinesDeSemana);

        return fechaDevolucion;
    }

    private long contarFinesDeSemana(LocalDate fechaInicio, LocalDate fechaFin){
        long diferenciaEnDias = Duration.between(fechaInicio.atStartOfDay(), fechaFin.atStartOfDay()).toDays();
        int diaDeLaSemana = fechaInicio.getDayOfWeek().getValue();
        long cantidadFinesDeSemana = 0;

        if (diferenciaEnDias > 0) {
            boolean comienzaEnDiaLaboral = diaDeLaSemana < 6;
            if (comienzaEnDiaLaboral) {
                cantidadFinesDeSemana =  (diferenciaEnDias + diaDeLaSemana - 1) / 5 * 2;
            } else {
                cantidadFinesDeSemana =  (diferenciaEnDias - 1) / 5 * 2 + (7 - diaDeLaSemana);
            }
        }
        return cantidadFinesDeSemana;
    }

}
