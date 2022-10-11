package com.ayi.trabajo_final.app.services.Impl;

import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerDetailEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.exceptions.WriteAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerDetailMapper;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerDetailRepository;
import com.ayi.trabajo_final.app.services.ICustomerDetailService;
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
public class CustomerDetailServiceImpl implements ICustomerDetailService {

    @Autowired
    private ICustomerDetailRepository customerDetailRepository;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;
    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public CustomerDetailResponseDTO addCustomerDetail(CustomerDetailDTO customerDetailDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException {
        CustomerDetailResponseDTO customerDetailResponseDTO;

        if (ObjectUtils.isEmpty(customerDetailDTO)) {
            throw new ReadAccessException("Error datos de la DTO estan vacios");
        }

        CustomerDetailEntity entity = customerDetailMapper.dtoToEntity(customerDetailDTO);

        entity.setCustomer(customerMapper.responseDTOToEntity(customerRDTO));

        try{

            customerDetailRepository.save(entity);
            customerDetailResponseDTO = customerDetailMapper.entityToDto(entity);
            return customerDetailResponseDTO;
        } catch (RuntimeException th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", customerRDTO.getFirstName() + " "+ customerRDTO.getLastName(), th.getStackTrace());
//            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            throw new RuntimeException("" + th.getStackTrace());
        }

    }
    @Override
    public CustomerDetailResponseDTO findDetailById(Long idDetail) throws ReadAccessException {
        CustomerDetailResponseDTO customerDetailResponseDTO;

        if (idDetail == null || idDetail <= 0) {
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(idDetail); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        CustomerDetailResponseDTO entity_RDTO = CustomerDetailResponseDTO.builder()
                .vip(entity.get().getVip())
                .totalPoints(entity.get().getTotalPoints())
                .build();
        return entity_RDTO;
    }

    @Override
    public void removeCustomerDetailById(Long id) throws ReadAccessException {

        if (id == null || id == 0 || id < 0) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(id); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        try {
            customerDetailRepository.deleteById(entity.get().getId());
            log.info("Completed Person data physical removal physical id={}", id);
        } catch (Throwable e) {
            log.error("Can't remove List Person data physical removal data={}, cause={}", id, e.getMessage());
            throw new RuntimeException("Error de base de datos no controlado");
        }



    }

    @Override
    public CustomerDetailResponseDTO updateCustomerDetailById(Long id, CustomerDetailDTO customerDTO) throws ReadAccessException {

        if (id == null || id == 0L || id < 0L) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(id);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error identificador no existe: " + id);
        }

        try {
            CustomerDetailEntity customerRequest = customerDetailMapper.dtoToEntity(customerDTO);
            customerDetailRepository.save(customerRequest);

            return customerDetailMapper.entityToDto(customerRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", th.getMessage());
            throw new WriteAccessException("Error no identificado de runtime");

        }
    }


}
