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
import java.util.Random;


@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private EstatusRepository estatusRepository;

    /**
     * Obtener una denuncia por su id
     */
    public List<Denuncia> obtenerTodasLasDenuncias() {
        return denunciaRepository.findAll();
    }

    /**
     * Registrar una nueva denuncia
     */
    public Denuncia registrarDenuncia(Denuncia denuncia) {
        String folio = String.format("%05d", new Random().nextInt(100000));
        denuncia.setFolio(folio);

        Estatus estatusInicial = estatusRepository.findByNombre("En Proceso")
                .orElseThrow(() -> new RuntimeException("Estatus 'En Proceso' no encontrado"));

        denuncia.setEstatus(estatusInicial);

        return denunciaRepository.save(denuncia);
    }


    /**
     * Obtener una denuncia por su folio y contraseña (para el seguimiento)
     */
    public Optional<Denuncia> obtenerDenunciaPorFolioYPassword(String folio, String password) {
        return denunciaRepository.findByFolioAndPassword(folio, password);
    }

    /**
     * Actualizar una denuncia
     */
    public Denuncia actualizarDenuncia(Long id, Denuncia actualizacion) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));
        return denunciaRepository.save(denuncia);
    }

    public ResponseEntity<Denuncia> obtenerDenunciaById(Long id) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        return denuncia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cambiar el estatus de una denuncia
     */
    public Denuncia cambiarEstatus(Long id, Long estatusId) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));

        Estatus estatus = estatusRepository.findById(estatusId)
                .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

        denuncia.setEstatus(estatus);
        return denunciaRepository.save(denuncia);
    }

    /**
     * Metodo para obtener el detalle de una denuncia por su ID
     */
    public Denuncia obtenerDenunciaPorId(Long id) {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));
    }

    /**
     * Metodo para ver una denuncia con su folio y contraseña
     */
    public Denuncia seguirDenuncia(String folio, String password) {
        return denunciaRepository.findByFolioAndPassword(folio, password)
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada o contraseña incorrecta"));
    }

}
