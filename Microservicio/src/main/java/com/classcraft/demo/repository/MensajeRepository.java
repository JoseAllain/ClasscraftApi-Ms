package com.classcraft.demo.repository;

import com.classcraft.demo.model.usuarios.Usuario;
import com.classcraft.demo.view.MensajeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.classcraft.demo.model.Mensaje;

import java.util.List;


@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
    @Query("SELECT NEW com.classcraft.demo.view.MensajeResponse(m.asunto,m.contenidoM,m.fechaEnvio,m.remitente.nombreU) FROM Mensaje m WHERE m.destinatario=:destinatario")
    List<MensajeResponse> findAllByDestinatario(@Param("destinatario") Usuario destinatario);

    @Query("SELECT NEW com.classcraft.demo.view.MensajeResponse(m.asunto,m.contenidoM,m.fechaEnvio,m.destinatario.nombreU) FROM Mensaje m WHERE m.remitente=:remitente")
    List<MensajeResponse> findAllByRemitente(@Param("remitente") Usuario remitente);
}