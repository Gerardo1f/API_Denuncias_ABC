package com.denuncias.repository;


import com.denuncias.model.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatusRepository extends JpaRepository<Estatus, Long>
{

}
