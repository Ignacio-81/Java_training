package com.ayi.trabajo_final.app.services.Impl;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
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
import java.util.Optional;
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
    public TicketResponseDTO addTicket(TicketDTO ticketDTO) throws ReadAccessException, DataBaseException {
        TicketResponseDTO ticketResponseDTO;

        if (ObjectUtils.isEmpty(ticketDTO)) {
            throw new ReadAccessException("Error datos de la DTO estan vacios");
        }

 //       TicketEntity t_entity = ticketMapper.dtoToEntity(ticketDTO);
        TicketEntity t_entity = new TicketEntity(
                ticketDTO.getDescription(),
                ticketDTO.getTotal()
        );
        CustomerResponseDTO c_entityDTO = new CustomerResponseDTO();
        //Check if customer exists by Document Number:
        Optional<CustomerEntity> entity_check = customerRepository.getCustomerByDNI(ticketDTO.customer.getDocumentNumber());

        if (entity_check.isPresent()) {

            t_entity.setCustomer(entity_check.get());
            c_entityDTO = customerMapper.entityToDto(entity_check.get());
/*        CustomerEntity c_entity = new CustomerEntity(
                ticketDTO.getCustomer().getFirstName(),
                ticketDTO.getCustomer().getLastName(),
                ticketDTO.getCustomer().getDocumentNumber(),
                LocalDate.now(),
                LocalDate.now()
        );

        List<TicketEntity> l_t_entity = new ArrayList<>();
        l_t_entity.add(t_entity);
        c_entity.setTickets(l_t_entity);*/

        }else {
            c_entityDTO = customerService.addCustomerTicket(ticketDTO.getCustomer());
            t_entity.setCustomer(customerMapper.responseDTOToEntity(c_entityDTO));
        }

        try{

            ticketsRespository.save(t_entity);
            ticketResponseDTO = ticketMapper.entityToDto(t_entity);
            ticketResponseDTO.setCustomer(c_entityDTO);
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
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        ticketResponseDTO = ticketMapper.entityToDto(entity.get());
        return ticketResponseDTO;
    }

    @Override
    public void removeTicketsById(Long idTicket) throws ReadAccessException {

        if (idTicket == null || idTicket == 0 || idTicket < 0) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id buscado");
        }

        try {
            ticketsRespository.deleteById(entity.get().getId());
            log.info("Completed Person data physical removal physical id={}", idTicket);
        } catch (Throwable e) {
            log.error("Can't remove List Person data physical removal data={}, cause={}", idTicket, e.getMessage());
            throw new RuntimeException("Error de base de datos no controlado");
        }



    }

    @Override
    public TicketResponseDTO updateTicketById(Long idTicket, TicketDTO ticketDTO) throws ReadAccessException {

        if (idTicket == null || idTicket == 0L || idTicket < 0L) {
            throw new ReadAccessException("Error el id a buscar es nulo o vacio");
        }

        Optional<TicketEntity> entity = ticketsRespository.findById(idTicket);


        if (!entity.isPresent()) { //Verifico que la persona a modificar existe
            throw new ReadAccessException("Error identificador de Ticket no existe: " + idTicket);
        }

        try {
            TicketEntity ticketRequest = ticketMapper.dtoToEntity(ticketDTO);
            ticketsRespository.save(ticketRequest);

            return ticketMapper.entityToDto(ticketRequest);
        } catch (Exception th) {
            log.error("Found an error when saving List Master Type code={}, cause={}", ticketDTO.getDescription(), th.getMessage());
            throw new WriteAccessException("Error no identificado de runtime");

        }
    }

}
