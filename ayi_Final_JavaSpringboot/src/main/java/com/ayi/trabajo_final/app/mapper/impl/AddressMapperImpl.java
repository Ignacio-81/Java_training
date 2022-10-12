package com.ayi.trabajo_final.app.mapper.impl;
/**
 * Address Mapper Implementation
 *
 */

import com.ayi.trabajo_final.app.dto.requests.AddressDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.entities.AddressEntity;
import com.ayi.trabajo_final.app.mapper.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl extends Exception implements IAddressMapper {
    private final ModelMapper modelMapper;
    @Override
    public AddressResponseDTO entityToDto(AddressEntity entity) {

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        modelMapper.map(entity, addressResponseDTO);
        return addressResponseDTO;
    }
    @Override
    public AddressEntity dtoToEntity(AddressDTO dto) {
        AddressEntity addressEntity = new AddressEntity();
        modelMapper.map(dto, addressEntity);
        return addressEntity;
    }
}
