package agendia.manejo_clientes.controller;

import agendia.manejo_clientes.model.dto.*;
import agendia.manejo_clientes.service.CustomerService;
import agendia.manejo_clientes.service.SendEmailService;
import agendia.manejo_clientes.service.VerificationCodeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final SendEmailService sendEmailService;
    private final VerificationCodeService verificationCodeService;

    public CustomerController(CustomerService customerService, SendEmailService sendEmailService, VerificationCodeService verificationCodeService) {
        this.customerService = customerService;
        this.sendEmailService = sendEmailService;
        this.verificationCodeService = verificationCodeService;
    }

    // http://localhost:8080/customer/upsert
    @PutMapping("/upsert")
    public ResponseEntity<EmailResponse> upsert(@RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.created(URI.create("/customer/" + customerRequest.email()))
                .body(sendEmailService.sendVerificationEmail(customerRequest));
    }

    // http://localhost:8080/customer
    @GetMapping
    public List<CustomerResponse>  findAll(){
      return customerService.findAll();
    }

    // http://localhost:8080/customer/juda@dev.com
    @GetMapping("/{email}")
    public CustomerResponse findByEmail(@PathVariable String email){

        return customerService.findByEmail(email);
    }

    // http://localhost:8080/customer/juda@dev.com
    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email){
        customerService.deleteByEmail(email);
    }

    // http://localhost:8080/customer/verify-code
    @PostMapping("/verify-code")
    public ConfirmationResponse verificationCode(@RequestBody @Valid ConfirmationRequest request){
        return verificationCodeService.codeValidation(request);
    }

}
