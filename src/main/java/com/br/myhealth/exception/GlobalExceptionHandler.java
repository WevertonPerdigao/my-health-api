package com.br.myhealth.exception;

import com.br.myhealth.controller.dto.ErroModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroModelDTO handlerEntityNotFound(EntityNotFoundException ex, WebRequest web) {
        return new ErroModelDTO("Entidade não encontrada", ex.getMessage());
    }

    @ExceptionHandler(HealthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroModelDTO handlerNegocioException(HealthException ex, WebRequest web) {
        return new ErroModelDTO(ex.getCause() != null ?
                ex.getCause().getMessage() : ex.toString(),
                ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroModelDTO handlerAcessDenied(AccessDeniedException ex, WebRequest web) {
        return new ErroModelDTO("acesso negado", ex.getMessage());
    }


}
