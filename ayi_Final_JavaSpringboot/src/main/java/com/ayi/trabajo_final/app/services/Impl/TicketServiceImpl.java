package com.ayi.trabajo_final.app.services.Impl;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.entities.TicketEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.mapper.ITicketMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerRepository;
import com.ayi.trabajo_final.app.repositories.ITicketRespository;
import com.ayi.trabajo_final.app.services.ICustomerService;
import com.ayi.trabajo_final.app.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class TicketServiceImpl implements ITicketService {
    @Autowired // Le digo que es una "tubería", genera un puente entre una cosa y otra para traer o enviar info
    private ITicketRespository ticketsRespository; // Fijate que no hicimos implementación de IPersonRepository, ya con esto es suficiente

    @Autowired
    private ITicketMapper ticketMapper; // Acá uso los mapper (me transforma una entidad a otra)

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public TicketResponseDTO addTicket(TicketDTO ticketDTO) throws ReadAccessException, DataBaseException {
        TicketResponseDTO ticketResponseDTO;

        if (ObjectUtils.isEmpty(ticketDTO)) {
            throw new ReadAccessException("Error datos de la DTO estan vacios");
        }

 //       TicketEntity t_entity = ticketMapper.dtoToEntity(ticketDTO);
        TicketEntity t_entity = new TicketEntity(
                ticketDTO.getDescription(), ticketDTO.getTotal()
        );

        Optional<CustomerEntity> entity_check = customerRepository.getCustomerByDNI(ticketDTO.customer.getDocumentNumber());

        if (entity_check.isPresent()) {

            t_entity.setCustomer(entity_check.get());
            ticketsRespository.save(t_entity);
        }else {
            CustomerResponseDTO c_responseDTO =  customerService.addCustomer(ticketDTO.getCustomer());
            CustomerEntity c_entity = new CustomerEntity(
                    c_responseDTO.getFirstName(),
                    c_responseDTO.getLastName(),
                    c_responseDTO.getDocumentNumber()
            );
            t_entity.setCustomer(c_entity);
        }

        try{
            ticketsRespository.save(t_entity);
            ticketResponseDTO = ticketMapper.entityToDto(t_entity);
            return ticketResponseDTO;
        } catch (RuntimeException th) {
/*            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());*/
            throw new RuntimeException("" + th.getStackTrace());
        }
    }

}
