package com.ayi.trabajo_final.app.mapper;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;

public interface ICustomerMapper {

    CustomerResponseDTO entityToDto(CustomerEntity entity);

    CustomerEntity dtoToEntity(CustomerDTO dto);

    CustomerEntity toEntityByRequest(CustomerDTO dto);

}
