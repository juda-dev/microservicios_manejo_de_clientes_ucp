package agendia.manejo_clientes.controller;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.dto.ClientResponse;
import agendia.manejo_clientes.model.entity.ClientEntity;
import agendia.manejo_clientes.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // http://localhost:8080/client/save
    @PostMapping("/save")
    public ResponseEntity<ClientEntity> save(@RequestBody @Valid ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.save(clientRequest));
    }

    // http://localhost:8080/client
    @GetMapping
    public List<ClientResponse>  findAll(){
      return clientService.findAll();
    }

    // http://localhost:8080/client/1
    @GetMapping("/{idCard}")
    public ClientResponse findByIdCard(@PathVariable Long idCard){
        return clientService.findByIdCard(idCard);
    }

    // http://localhost:8080/client/1
    @DeleteMapping("/{idCard}")
    public void deleteByIdCard(@PathVariable Long idCard){
        clientService.deleteByIdCard(idCard);
    }

    // http://localhost:8080/client

    @PutMapping
    public ClientResponse update(@RequestBody ClientRequest clientRequest){
        return clientService.update(clientRequest);
    }


}
