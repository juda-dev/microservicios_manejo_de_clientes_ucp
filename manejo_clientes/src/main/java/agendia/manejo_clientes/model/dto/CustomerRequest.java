package agendia.manejo_clientes.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(@NotNull Long idCard
        , @NotBlank String fullname
        , @NotBlank String email
        , @NotNull Long phone) {
}
