package agendia.manejo_clientes.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmationRequest(@NotBlank String email
        ,@NotBlank String code
        ,@NotNull Long userId) {
}
