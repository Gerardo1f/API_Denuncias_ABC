package com.denuncias.repository;

import com.denuncias.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {


    // Buscar una denuncia por su folio
    Optional<Denuncia> findByFolio(String folio);

    // Buscar una denuncia por su folio y contraseña para el seguimiento
    Optional<Denuncia> findByFolioAndPassword(String folio, String password);
}

