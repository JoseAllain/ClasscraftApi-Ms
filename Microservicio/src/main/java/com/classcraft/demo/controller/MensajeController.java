package com.classcraft.demo.controller;

import com.classcraft.demo.model.Mensaje;
import com.classcraft.demo.model.usuarios.Usuario;
import com.classcraft.demo.repository.UsuarioRepository;
import com.classcraft.demo.service.MensajeService;
import com.classcraft.demo.view.MensajeRequest;
import com.classcraft.demo.view.MensajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/mensaje/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody MensajeRequest mensajeRequest) {
        try {
            MensajeResponse mensajeResponse = mensajeService.enviarMensaje(
                    mensajeRequest.getAsunto(),
                    mensajeRequest.getContenido(),
                    mensajeRequest.getRemitenteId(),
                    mensajeRequest.getNombreDestinatario()
            );
            return ResponseEntity.ok(mensajeResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/mensaje/ver/recibidos/{id}")
    public List<MensajeResponse> verMensajesRecibidos(@PathVariable Long id){
        return mensajeService.verMensajesRecibidos(id);
    }

    @GetMapping("/mensaje/ver/enviados/{id}")
    public List<MensajeResponse> verMensajesEnviados(@PathVariable Long id){
        return mensajeService.verMensajesEnviados(id);
    }
}
