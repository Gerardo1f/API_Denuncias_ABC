package com.denuncias.service;

import com.denuncias.model.Estados;
import com.denuncias.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadosService
{
    @Autowired
    private EstadosRepository estadosRepository;

    public List<String> getEstadosByIdPais(Long idPais)
    {
        return estadosRepository.findNombreByIdPais(idPais);
    }
}
