package com.ceiba.biblioteca.controller;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoCreadoResponse;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.exceptions.AccionNoPermitidaException;
import com.ceiba.biblioteca.exceptions.BibliotecaException;
import com.ceiba.biblioteca.exceptions.PrestamoNoEncontradoException;
import com.ceiba.biblioteca.exceptions.TipoUsuarioNoPermitidoException;
import com.ceiba.biblioteca.repository.PrestamoRepository;
import com.ceiba.biblioteca.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    private final PrestamoService prestamoService;

    private final PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoService prestamoService, PrestamoRepository prestamoRepository){
        this.prestamoService = prestamoService;
        this.prestamoRepository = prestamoRepository;
    }

    @PostMapping
    public ResponseEntity<PrestamoCreadoResponse> prestarLibro(@RequestBody PrestamoRequest prestamoRequest) {
        try {
            PrestamoCreadoResponse prestamoCreadoResponse = prestamoService.realizarPrestamo(prestamoRequest);
            return new ResponseEntity<>(prestamoCreadoResponse, HttpStatus.OK);
        } catch (AccionNoPermitidaException e) {
            throw new AccionNoPermitidaException(e.getMessage());
        }catch (TipoUsuarioNoPermitidoException e){
            throw new TipoUsuarioNoPermitidoException(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity(new BibliotecaException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> consultarPrestamoPorId(@PathVariable long id) {
        try{
            PrestamoResponse prestamoResponse = prestamoService.consultarPrestamo(id);
            return new ResponseEntity<>(prestamoResponse, HttpStatus.OK);
        }catch (PrestamoNoEncontradoException e){
            throw new PrestamoNoEncontradoException(e.getMessage());
        }
    }

}
