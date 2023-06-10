package com.prueba.tecnica.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidation {

    private String codigo;
    private String mensaje;
}
