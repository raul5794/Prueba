package com.prueba.tecnica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Column(name = "nombre")
    @NotBlank
    @NotNull
    @Size(min = 2,max = 15, message = "Los nombres tienen entre 2 y 15 letras")
    private String nombre;

    @Column(name="apellido")
    @NotBlank
    @NotNull
    @Size(min = 2,max = 20, message = "Los apellidos tienen entre 2 y 20 letras")
    private String apellido;

    @Column(name = "edad")
    @Min(value = 0)
    @Max(value = 115)
    private int edad;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @Past
    private Date fechaNacimiento;
}
