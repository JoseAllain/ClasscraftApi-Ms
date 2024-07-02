package com.classcraft.demo.controller;

import com.classcraft.demo.service.PadreService;
import com.classcraft.demo.view.MisHijosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PadreController {
    @Autowired
    private PadreService padreService;

    @GetMapping("/{padreId}/hijos/nombres")
    public ResponseEntity<List<MisHijosDTO>> obtenerNombresDeHijos(@PathVariable Long padreId) {
        List<MisHijosDTO> misHijosDTOS = padreService.obtenerNombresDeHijos(padreId);
        return ResponseEntity.ok(misHijosDTOS);
    }
}
