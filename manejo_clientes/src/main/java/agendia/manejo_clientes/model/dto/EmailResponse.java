package agendia.manejo_clientes.model.dto;

import java.time.LocalDateTime;

public record EmailResponse(String to, LocalDateTime date) {
}
