package com.classcraft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classcraft.demo.model.Inscripcion;


@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    
}