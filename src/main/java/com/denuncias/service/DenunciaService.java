package com.denuncias.service;

import com.denuncias.model.Denuncia;
import com.denuncias.model.Estatus;
import com.denuncias.repository.DenunciaRepository;
import com.denuncias.repository.EstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private EstatusRepository estatusRepository;

    // Obtener una denuncia por su ID
    public Optional<Denuncia> obtenerDenunciaPorId(String folio) {
        return denunciaRepository.findByFolio(folio);
    }
    // Registrar una nueva denuncia (la base de datos generará el folio)
    public Denuncia registrarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    // Obtener una denuncia por su folio y contraseña (para el seguimiento)
    public Optional<Denuncia> obtenerDenunciaPorFolioYPassword(String folio, String password) {
        return denunciaRepository.findByFolioAndPassword(folio, password);
    }

    // Actualizar una denuncia (cambiar estatus, agregar comentarios, etc.)
    public Denuncia actualizarDenuncia(Long id, Denuncia actualizacion) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));
        // Actualizar los campos necesarios aquí
        return denunciaRepository.save(denuncia);
    }

    public ResponseEntity<Denuncia>obtenerDenunciaById(Long id)
    {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        return denuncia.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    public Denuncia cambiarEstatus(Long id, Long estatusId) {
        Denuncia denuncia = denunciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));
        Estatus estatus = estatusRepository.findById(estatusId).orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
        denuncia.setEstatus(estatus);
        return denunciaRepository.save(denuncia);
    }


}
