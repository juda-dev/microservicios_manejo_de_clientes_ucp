package agendia.manejo_clientes.repository;

import agendia.manejo_clientes.model.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodeEntity, String> {
}
