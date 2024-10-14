package com.denuncias.repository;


import com.denuncias.model.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstatusRepository extends JpaRepository<Estatus, Long>
{
    Optional<Estatus> findByNombre(String nombre);

}
