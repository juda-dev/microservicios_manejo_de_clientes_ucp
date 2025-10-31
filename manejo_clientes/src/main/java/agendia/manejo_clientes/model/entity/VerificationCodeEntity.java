package agendia.manejo_clientes.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_codes")
public class VerificationCodeEntity {

    @Id
    @Column(name = "customer_email")
    private String customerEmail;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, name = "expiration_date")
    private LocalDateTime expirationDate;

    public VerificationCodeEntity() {
    }

    public VerificationCodeEntity(String customerEmail, String code, LocalDateTime expirationDate) {
        this.customerEmail = customerEmail;
        this.code = code;
        this.expirationDate = expirationDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}

