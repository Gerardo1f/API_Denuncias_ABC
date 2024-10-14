package com.denuncias.repository;

import com.denuncias.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    Optional<Denuncia> findByFolioAndPassword(String folio, String password);
}

