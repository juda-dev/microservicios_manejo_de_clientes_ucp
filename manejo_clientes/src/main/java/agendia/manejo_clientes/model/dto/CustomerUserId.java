package agendia.manejo_clientes.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record CustomerUserId( @Column(name = "customer_id", nullable = false) Long customerId
        , @Column(name = "user_id", nullable = false) Long userId) implements Serializable {
}
