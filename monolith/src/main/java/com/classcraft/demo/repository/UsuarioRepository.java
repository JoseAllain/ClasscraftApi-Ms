package com.classcraft.demo.repository;

import com.classcraft.demo.model.usuarios.Padre;
import com.classcraft.demo.model.usuarios.Usuario;
import com.classcraft.demo.view.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByCorreoElectronico(String correo);

    Optional<Usuario> findById(Long id);
}
