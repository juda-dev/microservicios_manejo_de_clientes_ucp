package agendia.manejo_clientes.mapper;

import agendia.manejo_clientes.model.dto.CustomerRequest;
import agendia.manejo_clientes.model.dto.CustomerResponse;
import agendia.manejo_clientes.model.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity requestToEntity(CustomerRequest clientResponse){
        return new CustomerEntity(clientResponse.idCard(),
                clientResponse.fullname(),
                clientResponse.email(),
                clientResponse.phone());
    }

    public static CustomerResponse EntityToResponse(CustomerEntity customerEntity){
        return new CustomerResponse(customerEntity.getIdCard()
                , customerEntity.getPhone()
                , customerEntity.getEmail()
                , customerEntity.getFullName());
    }
}
