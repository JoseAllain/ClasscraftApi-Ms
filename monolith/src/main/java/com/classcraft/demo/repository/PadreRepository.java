package com.classcraft.demo.repository;

import com.classcraft.demo.model.usuarios.Padre;
import com.classcraft.demo.view.MisHijosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PadreRepository extends JpaRepository<Padre,Long> {
    public Optional<Padre> findByIdUsuario(Long id);

    @Query("SELECT new com.classcraft.demo.view.MisHijosDTO(e.idUsuario,e.nombreU) FROM Padre p JOIN p.estudiantes e WHERE p.idUsuario = :padreId")
    List<MisHijosDTO> findNombresDeHijosByPadreId(@Param("padreId") Long padreId);
}
