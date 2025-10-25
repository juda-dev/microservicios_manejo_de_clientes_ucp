package agendia.manejo_clientes.model.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(String code
        , HttpStatus status
        , String message
        , List<String> detailsMessages
        , LocalDateTime timeStamp) {}
