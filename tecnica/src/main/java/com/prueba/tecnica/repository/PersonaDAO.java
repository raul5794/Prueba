package com.prueba.tecnica.repository;

import com.prueba.tecnica.dto.KpiDTO;
import com.prueba.tecnica.entity.Persona;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaDAO extends JpaRepository<Persona,String> {

    List<Persona> findAll();

    Persona save(Persona persona);

    @Query(nativeQuery = true,value = "select avg(edad) from personas ")
    double avg();

    @Query(nativeQuery = true,value = "select STDDEV(edad) from personas ")
    double dsv();
}
