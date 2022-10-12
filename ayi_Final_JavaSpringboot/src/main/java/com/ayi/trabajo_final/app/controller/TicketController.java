package com.ayi.trabajo_final.app.controller;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.mapper.ICustomerMapper;
import com.ayi.trabajo_final.app.repositories.ICustomerRepository;
import com.ayi.trabajo_final.app.services.IAddressService;
import com.ayi.trabajo_final.app.services.ICustomerDetailService;
import com.ayi.trabajo_final.app.services.ICustomerService;
import com.ayi.trabajo_final.app.services.ITicketService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Api(value = "Ticket API", tags = {"Tickets Service"}) // Le decimos a Swagger que hay una transacción nueva, y que se llama Person Service (Es lo que se ve en grande en el Swagger)
@Slf4j // Esto es para el logeo
@RequestMapping(value = "/tickets", produces = {MediaType.APPLICATION_JSON_VALUE}) // Le indica al mundo externo que hay un servicio en la dirección web tal que se llama /persons, y lo que tiene que enviar es un JSON
@RestController // Esto es un controlador REST. @Controller es para un controlador MVC, no es REST
public class TicketController {

    @Autowired
    private ITicketService ticketService; // Acá traigo la interfaz del servicio
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private ICustomerDetailService iCustomerDetailService;
    @Autowired
    private IAddressService iAddressService;

    @PostMapping(
            value = "/addTicket"
    )
    @ApiOperation(
            value = "POST a new ticket on the Database",
            httpMethod = "POST",
            response = TicketResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, // la respuesta es CREATED
                    message = "New Ticket created"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error")
    })
    public ResponseEntity<?> postTicket(
            @ApiParam(name = "TicketDTO", value = "Payload data to create the new Ticket")
            @RequestBody TicketDTO ticketDTO) {
        log.info("Entering add Ticket [request]: {}", ticketDTO);

        Map<String, Object> response = new HashMap<>();
        TicketResponseDTO ticketResponseDTO = null;
        CustomerResponseDTO customerResponseDTO = null;
        AddressResponseDTO addressResponseDTO = null;
        CustomerDetailResponseDTO customerDetailResponseDTO = null;
        try {

            //Check if customer exists by Document Number:
            Optional<CustomerEntity> entity_check = customerRepository.getCustomerByDNI(ticketDTO.customer.getDocumentNumber());

            if (entity_check.isPresent()) {

                customerResponseDTO = customerMapper.entityToDto(entity_check.get());

            }else {
                customerResponseDTO = customerService.addCustomer(ticketDTO.getCustomer());
                customerDetailResponseDTO = iCustomerDetailService.addCustomerDetail(ticketDTO.getCustomer().getCustomerDetailDTO(), customerResponseDTO);
                addressResponseDTO = iAddressService.addAddress(ticketDTO.getCustomer().getAddressDTO(), customerResponseDTO);
                customerResponseDTO.setAddressResponseDTO(addressResponseDTO);
                customerResponseDTO.setCustomerDetailResponseDTO(customerDetailResponseDTO);
            }
            ticketResponseDTO = ticketService.addTicket(ticketDTO, customerResponseDTO);
            ticketResponseDTO.setCustomer(customerResponseDTO);

        } catch (ReadAccessException e) {
            response.put("Mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            response.put("Mensaje", "Error del sistema, pongase en contanto con el administrador");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataBaseException e) {
            response.put("Mensaje", "Prueba de error database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        log.info("Leaving addCustomer [response]: {}", TicketResponseDTO.builder().build().getIdTicket());
        return new ResponseEntity<>(ticketResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/getTicketById/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "GET",
            response = TicketResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with Ticket and customer Information by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<?> getTicketById(
            @ApiParam(name = "id", required = true, value = "Ticket Id", example = "1")
            @PathVariable("id") Long id) { // este "id" es lo que está entre llaves en el getmapping {id}

        Map<String, Object> response = new HashMap<>();
        TicketResponseDTO ticketResponseDTO = null;
        CustomerResponseDTO customerResponseDTO = null;
        try{
            ticketResponseDTO = ticketService.findTicketById(id);
        } catch (ReadAccessException e) {
            response.put("Mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        log.info("Leaving getPersonById [response]: {}", TicketResponseDTO.builder().build().getIdTicket());
        return new ResponseEntity<>(ticketResponseDTO, HttpStatus.ACCEPTED);

    }
}
