package com.prueba.tecnica.service;

import com.prueba.tecnica.dto.KpiDTO;
import com.prueba.tecnica.entity.Persona;
import com.prueba.tecnica.repository.PersonaDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
public class PersonaService {

    private PersonaDAO personaDAO;
    private KpiDTO kpiDTO;

    @Autowired
    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public Persona crearPersona(Persona persona) {
        this.personaDAO.save(persona);
        return persona;
    }

    public KpiDTO kpi() {
        kpiDTO = new KpiDTO();
        kpiDTO.setAvgEdad(this.personaDAO.avg());
        kpiDTO.setDesvEstandar(this.personaDAO.dsv());
        return kpiDTO;
    }

    public List<Persona> listarPersonas() {
        return this.personaDAO.findAll();
    }
}
