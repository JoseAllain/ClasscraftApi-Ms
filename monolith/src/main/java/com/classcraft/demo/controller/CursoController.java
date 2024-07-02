package com.classcraft.demo.controller;

import com.classcraft.demo.model.Curso;
import com.classcraft.demo.service.CursoService;
import com.classcraft.demo.view.CursoDTO;
import com.classcraft.demo.view.CursoResponse;
import com.classcraft.demo.view.VerUsuariosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/curso")
    public ResponseEntity<CursoResponse> crearCurso(@RequestBody CursoDTO cursoDTO) {

        Curso curso = new Curso();
        curso.setNombreC(cursoDTO.getNombre());
        curso.setDescripcion(cursoDTO.getDescripcion());
        
        Curso nuevoCurso = cursoService.crearCurso(curso, cursoDTO.getMaestroId());

        CursoResponse cursoResponse = new CursoResponse(nuevoCurso.getIdCurso(), nuevoCurso.getNombreC(), nuevoCurso.getDescripcion());

        return new ResponseEntity<>(cursoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/maestro/{maestroId}/cursos")
    public ResponseEntity<List<CursoDTO>> obtenerCursosDeMaestro(@PathVariable Long maestroId) {
        try {
            List<Curso> cursos = cursoService.obtenerCursosPorMaestroId(maestroId);
            List<CursoDTO> cursosDTO = cursos.stream()
                    .map(curso -> new CursoDTO(curso.getIdCurso(), curso.getNombreC(), curso.getDescripcion(), curso.getMaestro().getIdUsuario()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(cursosDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/curso/usuarios/{id}")
    public List<VerUsuariosResponse> verUsuarios(@PathVariable Long id){
        return cursoService.verUsuarios(id);
    }





}