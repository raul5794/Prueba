package com.prueba.tecnica.handler;

import com.prueba.tecnica.dto.error.ErrorDetail;
import com.prueba.tecnica.dto.error.ErrorValidation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidadorError(MethodArgumentNotValidException manve, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if(requestPath == null){
            requestPath = request.getRequestURI();
        }
        errorDetail.setTitulo("Validación de Campos");
        errorDetail.setDetalle("Error al validar Campos");
        errorDetail.setDesarrolladorMensaje(manve.getClass().getName());

        List<FieldError> fieldErrorList = manve.getBindingResult().getFieldErrors();

        for(FieldError fieldError: fieldErrorList){

            List<ErrorValidation> errorValidationList = errorDetail.getErrors().computeIfAbsent(fieldError.getField(), k -> new ArrayList<>());
            ErrorValidation errorValidation = new ErrorValidation();
            errorValidation.setCodigo(fieldError.getCode());
            errorValidation.setMensaje(fieldError.getDefaultMessage());
            errorValidationList.add(errorValidation);
        }
        return new ResponseEntity<>(errorDetail,null,HttpStatus.BAD_REQUEST);
    }
}
