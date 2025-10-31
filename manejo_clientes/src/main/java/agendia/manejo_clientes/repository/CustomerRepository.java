package agendia.manejo_clientes.repository;

import agendia.manejo_clientes.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByEmail(String email);
    void deleteByEmail(String email);

}
