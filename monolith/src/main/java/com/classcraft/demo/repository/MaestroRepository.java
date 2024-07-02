package com.classcraft.demo.repository;

import com.classcraft.demo.model.usuarios.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro,Long> {
    Optional<Maestro> findByNombreU(String nombre);
}
