package com.denuncias.service;

import com.denuncias.model.Pais;
import com.denuncias.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService
{
    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> getAllPais()
    {
        return paisRepository.findAll();
    }
}
