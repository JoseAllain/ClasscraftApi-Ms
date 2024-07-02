package com.classcraft.demo.service;

import com.classcraft.demo.exception.ContrasenaIncorrectaException;
import com.classcraft.demo.exception.CorreoYaRegistradoException;
import com.classcraft.demo.exception.PadreNoExisteException;
import com.classcraft.demo.exception.UsuarioNoExisteException;
import com.classcraft.demo.model.usuarios.Estudiante;
import com.classcraft.demo.model.usuarios.Padre;
import com.classcraft.demo.model.usuarios.Usuario;
import com.classcraft.demo.repository.EstudianteRepository;
import com.classcraft.demo.repository.MaestroRepository;
import com.classcraft.demo.repository.PadreRepository;
import com.classcraft.demo.repository.UsuarioRepository;
import com.classcraft.demo.view.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PadreRepository padreRepository;

    @Autowired
    private MaestroRepository maestroRepository;

    public Usuario iniciarSesion(String correoElectronico, String contrasena){

        Optional<Usuario> usuarioOpt=usuarioRepository.findByCorreoElectronico(correoElectronico);
        if(usuarioOpt.isEmpty()){
            throw new UsuarioNoExisteException("Correo no valido vuelva a intentar");
        }

        Usuario usuario=usuarioOpt.get();

        if (!usuario.getContrasena().equals(contrasena)){
            throw new ContrasenaIncorrectaException("La contraseña es incorrecta");
        }

        return usuario;
    }

    public Usuario registrarUsuario(Usuario usuario){

        Optional<Usuario> usuarioOptional=usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());

        if (usuarioOptional.isPresent()){
           throw new CorreoYaRegistradoException("El correo indicado ya esta registrado");
        }

        return usuarioRepository.save(usuario);
    }

    public List<LoginResponse> listarUsuarios(){
        return usuarioRepository.listarUsuarios();
    }

    public Padre buscarPadre(Long id){
        Optional<Padre> padreOptional = padreRepository.findByIdUsuario(id);
        if (padreOptional.isEmpty()){
            throw new PadreNoExisteException("El ID Especificado no pertenece a una cuenta registrada como Padre");
        }
        return padreOptional.get();
    }
}
