package com.classcraft.demo.service;

import com.classcraft.demo.repository.PadreRepository;
import com.classcraft.demo.view.MisHijosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PadreService {
    @Autowired
    private PadreRepository padreRepository;

    public List<MisHijosDTO> obtenerNombresDeHijos(Long padreId) {
        return padreRepository.findNombresDeHijosByPadreId(padreId);
    }
}
