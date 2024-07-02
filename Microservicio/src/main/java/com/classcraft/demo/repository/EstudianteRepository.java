package com.classcraft.demo.repository;

import com.classcraft.demo.model.usuarios.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
}
