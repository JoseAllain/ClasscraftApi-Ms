package com.classcraft.demo.controller;

import com.classcraft.demo.service.EstudianteService;
import com.classcraft.demo.view.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/estudiante/{estudianteId}/cursos")
    public ResponseEntity<List<CursoDTO>> listarCursosEstudiante(@PathVariable Long estudianteId) {
        try {
            List<CursoDTO> cursosDTO = estudianteService.obtenerCursosPorEstudianteId(estudianteId);
            return ResponseEntity.ok(cursosDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
