package com.ayi.trabajo_final.app.services.Impl;
/**
 * Customer Detail Service Implementation
 * @Transactional
 *
 */
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

        if (ObjectUtils.isEmpty(customerDetailDTO) ||ObjectUtils.isEmpty(customerRDTO) ) {
            throw new ReadAccessException("Error DTO data is empty");
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
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(idDetail); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
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
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(id); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        try {
            customerDetailRepository.deleteById(entity.get().getId());
            log.info("Completed Customer Detail data physical removal physical id={}", id);
        } catch (Throwable e) {
            log.error("Can't remove Customer Detail data physical removal data={}, cause={}", id, e.getMessage());
            throw new RuntimeException("Database Error not handled");
        }



    }

    @Override
    public CustomerDetailResponseDTO updateCustomerDetailById(Long id, CustomerDetailDTO customerDTO) throws ReadAccessException {

        if (id == null || id == 0L || id < 0L) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerDetailEntity> entity = customerDetailRepository.findById(id);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error, no data for this ID " + id);
        }

        try {
            CustomerDetailEntity customerRequest = customerDetailMapper.dtoToEntity(customerDTO);
            customerDetailRepository.save(customerRequest);

            return customerDetailMapper.entityToDto(customerRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", th.getMessage());
            throw new WriteAccessException("Runtime undefined Error");

        }
    }


}
