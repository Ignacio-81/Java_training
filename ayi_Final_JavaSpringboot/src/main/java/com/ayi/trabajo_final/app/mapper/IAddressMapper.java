package com.ayi.trabajo_final.app.mapper;

import com.ayi.trabajo_final.app.dto.requests.AddressDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.entities.AddressEntity;

public interface IAddressMapper {
    AddressResponseDTO entityToDto(AddressEntity entity);

    AddressEntity dtoToEntity(AddressDTO dto);
}
