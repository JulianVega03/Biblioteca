package com.ceiba.biblioteca.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "isbn", nullable = false, updatable = false)
    @NotBlank(message = "Isbn es requerido")
    @Size(max = 10, message = "ISBN debe contener maximo 10 caracteres")
    private String isbn;

    @Column(name = "id_usuario", nullable = false, updatable = false)
    @NotBlank(message = "Identificador de Usuario es requerido")
    @Size(max = 10, message = "Identificador de Usuario debe contener maximo 10 caracteres")
    private String idUsuario;

    @Column(name = "tipo_usuario", nullable = false, updatable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "fecha_devolucion", nullable = false, updatable = false)
    @NotNull
    private LocalDate fechaDevolucion;

    public Prestamo() {
    }

    public Prestamo(
            @NotBlank(message = "Isbn es requerido")
            @Size(max = 10, message = "ISBN debe contener maximo 10 caracteres") String isbn,
            @NotBlank(message = "Identificador de Usuario es requerido")
            @Size(max = 10, message = "Identificador de Usuario debe contener maximo 10 caracteres") String idUsuario,
            @NotBlank(message = "Tipo de Usuario es requerido") TipoUsuario tipoUsuario, @NotNull LocalDate fechaDevolucion
            )
    {
        this.isbn = isbn;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaDevolucion = fechaDevolucion;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

}
