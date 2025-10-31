package agendia.manejo_clientes.controller;

import agendia.manejo_clientes.exceptions.CustomerNotFoundException;
import agendia.manejo_clientes.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

import static agendia.manejo_clientes.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handlerClientNotFoundException(){
        return new ErrorResponse(CUSTOMER_NOT_FOUND.getCode()
                , HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND.getMessage()
                , null
                , LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        BindingResult result = ex.getBindingResult();

        return new ErrorResponse(INVALID_CUSTOMER.getCode()
                , HttpStatus.BAD_REQUEST
                , INVALID_CUSTOMER.getMessage()
                , result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList()
                , LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerException(Exception ex){

        return new ErrorResponse(GENERIC_ERROR.getCode()
                , HttpStatus.INTERNAL_SERVER_ERROR
                , GENERIC_ERROR.getMessage()
                , Collections.singletonList(ex.getMessage())
                , LocalDateTime.now());
    }
}
