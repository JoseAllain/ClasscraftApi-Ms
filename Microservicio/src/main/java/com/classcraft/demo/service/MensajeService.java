package com.classcraft.demo.service;

import com.classcraft.demo.model.Mensaje;
import com.classcraft.demo.model.usuarios.Usuario;
import com.classcraft.demo.repository.MensajeRepository;
import com.classcraft.demo.repository.UsuarioRepository;
import com.classcraft.demo.view.MensajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public MensajeResponse enviarMensaje(String asunto, String contenido, Long remitenteId, String nombreDestinatario) throws Exception {
        Usuario remitente = usuarioRepository.findByIdUsuario(remitenteId)
                .orElseThrow(() -> new Exception("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findByNombreU(nombreDestinatario)
                .orElseThrow(() -> new Exception("Destinatario no encontrado"));

        Mensaje mensaje = new Mensaje(asunto, contenido, remitente, destinatario);
        mensaje = mensajeRepository.save(mensaje);
        return new MensajeResponse(mensaje);
    }

    public List<MensajeResponse> verMensajesRecibidos(Long id){
        Usuario destinatario;
        Optional<Usuario> optionalUsuario=usuarioRepository.findByIdUsuario(id);
        if (optionalUsuario.isPresent()){
            destinatario=optionalUsuario.get();
        }else {
            return new ArrayList<>();
        }
        return mensajeRepository.findAllByDestinatario(destinatario);
    }

    public List<MensajeResponse> verMensajesEnviados(Long id){
        Usuario remitente;
        Optional<Usuario> optionalUsuario=usuarioRepository.findByIdUsuario(id);
        if (optionalUsuario.isPresent()){
            remitente=optionalUsuario.get();
        }else {
            return new ArrayList<>();
        }
        return mensajeRepository.findAllByRemitente(remitente);
    }
}
