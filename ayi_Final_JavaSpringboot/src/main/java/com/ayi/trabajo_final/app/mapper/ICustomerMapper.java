package com.ayi.trabajo_final.app.mapper;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;

public interface ICustomerMapper {

    CustomerResponseDTO entityToDto(CustomerEntity entity);

    CustomerEntity dtoToEntity(CustomerDTO dto);

    CustomerEntity responseDTOToEntity(CustomerResponseDTO dto);

    CustomerTicketsResponseDTO entityToCustomerTicketDto(CustomerEntity entity);

    /*    CustomerEntity toEntityByRequest(CustomerDTO dto);*/

}
