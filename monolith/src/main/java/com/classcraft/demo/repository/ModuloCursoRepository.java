package com.classcraft.demo.repository;

import com.classcraft.demo.model.Curso;
import com.classcraft.demo.model.ModuloCurso;
import com.classcraft.demo.view.ModuloCursoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloCursoRepository extends JpaRepository<ModuloCurso,Long> {
    @Query("select new com.classcraft.demo.view.ModuloCursoResponse(m.titulo) from ModuloCurso m where m.curso=:curso")
    List<ModuloCursoResponse> findAllByCurso(Curso curso);
}
