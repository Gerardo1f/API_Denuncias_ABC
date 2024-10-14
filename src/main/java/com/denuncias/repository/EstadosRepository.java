package com.denuncias.repository;

import com.denuncias.model.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadosRepository extends JpaRepository<Estados,Long>
{
    @Query(value = "select nombre from Estados where idPais = :idPais",nativeQuery = true)
    List<String>findNombreByIdPais(@Param("idPais")Long idPais);
}
