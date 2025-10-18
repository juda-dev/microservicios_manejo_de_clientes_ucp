package agendia.manejo_clientes.controller;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.entity.ClientEntity;
import agendia.manejo_clientes.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public ResponseEntity<ClientEntity> save(@RequestBody @Valid ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.save(clientRequest));
    }
}
