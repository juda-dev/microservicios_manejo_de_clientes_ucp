package agendia.manejo_clientes.model.dto;

import agendia.manejo_clientes.validation.ExistsEmail;
import agendia.manejo_clientes.validation.ExistsIdCard;
import agendia.manejo_clientes.validation.ExistsPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(@NotNull @ExistsIdCard Long idCard
        , @NotBlank String fullname
        , @NotBlank @ExistsEmail String email
        , @NotNull @ExistsPhone Long phone) {
}
