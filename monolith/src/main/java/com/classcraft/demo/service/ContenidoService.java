package com.classcraft.demo.service;




import com.classcraft.demo.model.ArchivoCS;
import com.classcraft.demo.model.Curso;


import com.classcraft.demo.model.ModuloCurso;
import com.classcraft.demo.repository.ArchivoCSRepository;
import com.classcraft.demo.repository.CursoRepository;

import com.classcraft.demo.repository.ModuloCursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ContenidoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModuloCursoRepository moduloCursoRepository;

    @Autowired
    private ArchivoCSRepository archivoCSRepository;
    //@Autowired
    //private UrlRepository urlRepository;

    @Transactional
    public ArchivoCS guardarArchivo(Long moduloId, String titulo, MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo no puede ser nulo o vacío.");
        }

        ModuloCurso moduloCurso = moduloCursoRepository.findById(moduloId)
                .orElseThrow(() -> new RuntimeException("Modulo no encontrado"));

        ArchivoCS nuevoArchivo = new ArchivoCS();
        nuevoArchivo.setTitulo(titulo);
        nuevoArchivo.setContenido(archivo.getBytes());
        nuevoArchivo.setModuloCurso(moduloCurso);

        return archivoCSRepository.save(nuevoArchivo);
    }

    public ArchivoCS obtenerArchivo(Long contenidoId) throws RuntimeException {
        Optional<ArchivoCS> resultado = archivoCSRepository.findById(contenidoId);
        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new RuntimeException("Archivo no encontrado con ID: " + contenidoId);
        }
    }

}
