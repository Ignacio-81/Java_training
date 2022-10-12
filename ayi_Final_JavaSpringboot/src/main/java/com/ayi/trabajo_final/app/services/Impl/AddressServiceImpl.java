package com.ayi.trabajo_final.app.services.Impl;
/**
 * Address Service Implementation
 * @Transactional
 *
 */
import com.ayi.trabajo_final.app.dto.requests.AddressDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.entities.AddressEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.exceptions.WriteAccessException;
import com.ayi.trabajo_final.app.mapper.IAddressMapper;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.repositories.IAddressRespository;
import com.ayi.trabajo_final.app.services.IAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service //Indica que es un servicio y puede ser inyectado
@Slf4j
@Transactional //Maneja la transaccion, hace el commit y maneja el rollback begin , commit, rollback
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private IAddressMapper addressMapper; // Acá uso los mapper (me transforma una entidad a otra)
    @Autowired
    private IAddressRespository addressRespository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public AddressResponseDTO addAddress(AddressDTO addressDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException {
        AddressResponseDTO addressResponseDTO;

        if (ObjectUtils.isEmpty(addressDTO)||ObjectUtils.isEmpty(customerRDTO) ) {
            throw new ReadAccessException("Error DTO data is empty");
        }

        AddressEntity entity = addressMapper.dtoToEntity(addressDTO);

        entity.setCustomer(customerMapper.responseDTOToEntity(customerRDTO));

        try{

            addressRespository.save(entity);
            addressResponseDTO = addressMapper.entityToDto(entity);
            return addressResponseDTO;
        } catch (RuntimeException th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", addressDTO.getStreetName() + " "+ addressDTO.getStreetNumber(), th.getStackTrace());
//            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            throw new RuntimeException("" + th.getStackTrace());
        }

    }
    @Override
    public AddressResponseDTO findAddressById(Long idAddress) throws ReadAccessException {
        AddressResponseDTO addressResponseDTO;

        if (idAddress == null || idAddress <= 0) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<AddressEntity> entity = addressRespository.findById(idAddress); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        addressResponseDTO = addressMapper.entityToDto(entity.get());
        return addressResponseDTO;
    }

    @Override
    public void removeAddressById(Long idAddress) throws ReadAccessException {

        if (idAddress == null || idAddress == 0 || idAddress < 0) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<AddressEntity> entity = addressRespository.findById(idAddress); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }
        try {
            addressRespository.deleteById(entity.get().getId());
            log.info("Completed Address data physical removal physical id={}", idAddress);
        } catch (Throwable e) {
            log.error("Can't remove List Person data physical removal data={}, cause={}", idAddress, e.getMessage());
            throw new RuntimeException("Database Error not handled");
        }



    }

    @Override
    public AddressResponseDTO updateAddressById(Long idAddress, AddressDTO addressDTO) throws ReadAccessException {

        if (idAddress == null || idAddress == 0L || idAddress < 0L) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<AddressEntity> entity = addressRespository.findById(idAddress);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error, no data for this ID " + idAddress);
        }

        try {
            AddressEntity addressRequest = addressMapper.dtoToEntity(addressDTO);
            addressRespository.save(addressRequest);

            return addressMapper.entityToDto(addressRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", addressDTO.getStreetName() + " " + addressDTO.getStreetNumber(), th.getMessage());
            throw new WriteAccessException("Runtime undefined Error");

        }
    }
}
