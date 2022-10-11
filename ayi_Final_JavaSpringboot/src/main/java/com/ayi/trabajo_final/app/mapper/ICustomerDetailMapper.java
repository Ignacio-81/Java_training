package com.ayi.trabajo_final.app.mapper;

import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerDetailEntity;
import com.ayi.trabajo_final.app.entities.CustomerEntity;

import java.io.Serializable;

public interface ICustomerDetailMapper {
    CustomerDetailResponseDTO entityToDto(CustomerDetailEntity entity);

    CustomerDetailEntity dtoToEntity(CustomerDetailDTO dto);
}
