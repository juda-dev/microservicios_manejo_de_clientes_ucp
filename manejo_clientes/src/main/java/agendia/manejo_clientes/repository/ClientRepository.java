package agendia.manejo_clientes.repository;

import agendia.manejo_clientes.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByIdCard(Long idCard);
    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
}
