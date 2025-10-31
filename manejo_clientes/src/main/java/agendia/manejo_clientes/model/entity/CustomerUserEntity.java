package agendia.manejo_clientes.model.entity;

import agendia.manejo_clientes.model.dto.CustomerUserId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customers_users")
public class CustomerUserEntity {

    @EmbeddedId
    private CustomerUserId id;

    public CustomerUserEntity() {
    }

    public CustomerUserEntity(CustomerUserId id) {
        this.id = id;
    }

    public CustomerUserId getId() {
        return id;
    }

    public void setId(CustomerUserId id) {
        this.id = id;
    }
}

