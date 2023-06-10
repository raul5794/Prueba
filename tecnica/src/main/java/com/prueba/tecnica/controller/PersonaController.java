package com.prueba.tecnica.controller;

import com.prueba.tecnica.entity.Persona;
import com.prueba.tecnica.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {

    private PersonaService personaService;

    @Autowired
    PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }

    @PostMapping(value="/creacliente")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Persona persona){
        this.personaService.crearPersona(persona);
        return new ResponseEntity<>(persona,null, HttpStatus.CREATED);
    }

    @GetMapping(value="/kpideclientes")
    public ResponseEntity<?> kpiClientes(){
        if(this.personaService.listarPersonas().size()<2){
            return new ResponseEntity<>("Deben haber al menos 2 registros",null,HttpStatus.OK);
        }
       return new ResponseEntity<>(this.personaService.kpi(),null,HttpStatus.OK) ;
    }

    @GetMapping(value = "/listclientes")
    public ResponseEntity<?> listarClientes(){
        if(this.personaService.listarPersonas().isEmpty()){
            return new ResponseEntity<>("No hay registros en la Base de Datos",null,HttpStatus.OK);
        }
        return new ResponseEntity<>(this.personaService.listarPersonas(),null,HttpStatus.OK);
    }
}
