package agendia.manejo_clientes.repository;

import agendia.manejo_clientes.model.dto.CustomerUserId;
import agendia.manejo_clientes.model.entity.CustomerUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerUserRepository extends JpaRepository<CustomerUserEntity, CustomerUserId> {
}
