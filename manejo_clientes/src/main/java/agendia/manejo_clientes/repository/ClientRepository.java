package agendia.manejo_clientes.repository;

import agendia.manejo_clientes.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsById(Long id);
    boolean existsByIdCard(Long idCard);
    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
    Optional<ClientEntity> findByIdCard(Long idCard);
    void deleteByIdCard(Long idCard);

}
