package com.ayi.trabajo_final.app.services.Impl;
/**
 * Ticket Service Implementation
 * @Transactional
 *
 */
import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.entities.TicketEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.exceptions.WriteAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.mapper.ITicketMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerRepository;
import com.ayi.trabajo_final.app.repositories.ITicketRespository;
import com.ayi.trabajo_final.app.services.ICustomerService;
import com.ayi.trabajo_final.app.services.ITicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service //Indica que es un servicio y puede ser inyectado
@Slf4j
@Transactional //Maneja la transaccion, hace el commit y maneja el rollback begin , commit, rollback
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
    public TicketResponseDTO addTicket(TicketDTO ticketDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException {
        TicketResponseDTO ticketResponseDTO;

        if (ObjectUtils.isEmpty(ticketDTO)) {
            throw new ReadAccessException("Error DTO Data is empty");
        }

        TicketEntity t_entity = new TicketEntity(
                ticketDTO.getDescription(),
                ticketDTO.getTotal()
        );
        t_entity.setCustomer(customerMapper.responseDTOToEntity(customerRDTO));
        try{

            ticketsRespository.save(t_entity);
            ticketResponseDTO = ticketMapper.entityToDto(t_entity);
            return ticketResponseDTO;
        } catch (RuntimeException th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", ticketDTO.getDescription(), th.getStackTrace());
//            log.error("Found an error when saving List Master Type code={}, cause={}", customerDTO.getFirstName() + " " + customerDTO.getLastName(), th.getStackTrace());
            throw new RuntimeException("" + th.getStackTrace());
        }
    }
    @Override
    public TicketResponseDTO findTicketById(Long idTicket) throws ReadAccessException {
        TicketResponseDTO ticketResponseDTO;

        if (idTicket == null || idTicket <= 0) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        ticketResponseDTO = ticketMapper.entityToDto(entity.get());
        return ticketResponseDTO;
    }

    @Override
    public void removeTicketsById(Long idTicket) throws ReadAccessException {

        if (idTicket == null || idTicket == 0 || idTicket < 0) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error, no data for this ID");
        }

        try {
            ticketsRespository.deleteById(entity.get().getId());
            log.info("Completed Ticket data physical removal physical id={}", idTicket);
        } catch (Throwable e) {
            log.error("Can't remove Ticket data physical removal data={}, cause={}", idTicket, e.getMessage());
            throw new RuntimeException("Database Error not handled");
        }


    }

    @Override
    public TicketResponseDTO updateTicketById(Long idTicket, TicketDTO ticketDTO) throws ReadAccessException {

        if (idTicket == null || idTicket == 0L || idTicket < 0L) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket);

        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error, no data for this ID " + idTicket);
        }

        try {
            TicketEntity ticketRequest = ticketMapper.dtoToEntity(ticketDTO);
            ticketsRespository.save(ticketRequest);

            return ticketMapper.entityToDto(ticketRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", ticketDTO.getDescription(), th.getMessage());
            throw new WriteAccessException("Runtime undefined Error");

        }
    }
    @Override
    public List<CustomerTicketsResponseDTO> findAllTicketByCustomerById(Long idCustomer) throws ReadAccessException {
        List<CustomerTicketsResponseDTO> ticketEntityList;

        if (idCustomer == null || idCustomer == 0L || idCustomer < 0L) {
            throw new ReadAccessException("ERROR ID must be greater than 0, not 'Null'");
        }

        Optional<CustomerEntity> entity = customerRepository.findById(idCustomer); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new ReadAccessException("Error: No data information with this ID");
        }
        ticketEntityList = entity.get().getTickets().stream()
                .map(lt -> new CustomerTicketsResponseDTO(
                        lt.getId(),
                        lt.getDescription(),
                        lt.getTotal()
                )) // Tdo esto es lo que estoy enviando al constructor de PersonResponseDTO
                .collect(Collectors.toList()); // A través de stream, mapeo los campos de personEntities a personResponseDTOs
        ;

        return ticketEntityList;
    }

}
