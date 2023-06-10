package com.prueba.tecnica.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

private String titulo;
private int status;

private String detalle;

private long timeStamp;

private String desarrolladorMensaje;

private Map<String, List<ErrorValidation> > errors= new HashMap<String,List<ErrorValidation>>();
 }
