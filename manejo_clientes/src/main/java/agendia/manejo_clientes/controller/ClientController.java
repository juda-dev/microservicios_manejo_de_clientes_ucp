package agendia.manejo_clientes.controller;

import agendia.manejo_clientes.model.dto.ClientRequest;
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
    public List<ClientEntity>  findAll(){
      return clientService.findAll();
    }

    // http://localhost:8080/client/1
    @GetMapping("/{id}")
    public ClientEntity findById(@PathVariable Long id){
        return clientService.findById(id);
    }

    // http://localhost:8080/client/1
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        clientService.deleteById(id);
    }

    // http://localhost:8080/client

    @PutMapping
    public ClientEntity update(@RequestBody ClientEntity clientEntity){
        return clientService.update(clientEntity);
    }


}
