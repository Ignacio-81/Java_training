package com.ayi.trabajo_final.app.mapper.impl;
/**
 * Customer Details Mapper Implementation
 *
 */
import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerDetailEntity;
import com.ayi.trabajo_final.app.mapper.ICustomerDetailMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerDetailMapperImpl extends Exception implements ICustomerDetailMapper {
    private final ModelMapper modelMapper;
    @Override
    public CustomerDetailResponseDTO entityToDto(CustomerDetailEntity entity) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(entity, customerDetailResponseDTO);
        return customerDetailResponseDTO;
    }
    @Override
    public CustomerDetailEntity dtoToEntity(CustomerDetailDTO dto) {
        CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();
        modelMapper.map(dto, customerDetailEntity);
        return customerDetailEntity;
    }
}
