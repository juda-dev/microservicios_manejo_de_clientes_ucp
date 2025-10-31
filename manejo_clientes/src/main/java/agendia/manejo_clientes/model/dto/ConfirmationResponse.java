package agendia.manejo_clientes.model.dto;

import java.time.LocalDateTime;

public record ConfirmationResponse(String customerFullName
        , Long userId
        , LocalDateTime schedulingDate) {
}
