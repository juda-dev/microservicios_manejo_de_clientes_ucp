package agendia.manejo_clientes.mapper;

import agendia.manejo_clientes.model.dto.ClientRequest;
import agendia.manejo_clientes.model.entity.ClientEntity;

public class ClientMapper {

    public static ClientEntity requestToEntity(ClientRequest clientResponse){
        return new ClientEntity(clientResponse.idCard(),
                clientResponse.fullname(),
                clientResponse.email(),
                clientResponse.phone());
    }
}
