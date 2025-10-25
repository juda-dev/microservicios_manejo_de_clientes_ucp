package agendia.manejo_clientes.mapper;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.dto.ClientResponse;
import agendia.manejo_clientes.model.entity.ClientEntity;

public class ClientMapper {

    public static ClientEntity requestToEntity(ClientRequest clientResponse){
        return new ClientEntity(clientResponse.idCard(),
                clientResponse.fullname(),
                clientResponse.email(),
                clientResponse.phone());
    }

    public static ClientResponse EntityToResponse(ClientEntity clientEntity){
        return new ClientResponse(clientEntity.getIdCard()
                , clientEntity.getPhone()
                , clientEntity.getEmail()
                , clientEntity.getFullname());
    }
}
