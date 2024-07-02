package com.classcraft.demo.controller;

import com.classcraft.demo.model.ArchivoCS;

import com.classcraft.demo.service.ContenidoService;
import com.classcraft.demo.view.ContenidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;

    @PostMapping("/{moduloId}/contenido")
    public ResponseEntity<?> subirArchivo(@PathVariable Long moduloId, @ModelAttribute ContenidoDTO contenidoDTO) {
        try {
            if ("archivo".equals(contenidoDTO.getTipo()) && contenidoDTO.getArchivo() != null) {
                contenidoService.guardarArchivo(moduloId, contenidoDTO.getTitulo(), contenidoDTO.getArchivo());
                contenidoDTO.setTipo("archivo");
                contenidoDTO.setArchivo(null);
            }
            // Crea una copia del DTO sin el archivo para la respuesta
            ContenidoDTO respuestaDTO = new ContenidoDTO(contenidoDTO.getTitulo(), contenidoDTO.getTipo(), null);
            return ResponseEntity.ok(respuestaDTO);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/archivo/{contenidoId}/obtener")
    public ResponseEntity<ByteArrayResource> descargarArchivo(@PathVariable Long contenidoId) {
        try {
            ArchivoCS archivo = contenidoService.obtenerArchivo(contenidoId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getTitulo() + "\"")
                    .body(new ByteArrayResource(archivo.getContenido()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
