package com.denuncias.service;

import com.denuncias.model.Empresas;
import com.denuncias.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresasService
{
    @Autowired
    private EmpresasRepository empresasRepository;

    public List<Empresas> getAllEmpresas()
    {
        return empresasRepository.findAll();
    }
}
