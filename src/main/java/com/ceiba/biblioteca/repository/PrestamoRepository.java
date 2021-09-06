package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.model.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

    boolean existsByIdUsuario(String idUsuario);

}
