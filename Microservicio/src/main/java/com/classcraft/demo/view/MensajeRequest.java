package com.classcraft.demo.view;

import com.classcraft.demo.model.usuarios.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MensajeRequest {
    String asunto;
    String contenido;
    Long remitenteId;
    String nombreDestinatario;

}
