package com.ayi.trabajo_final.app.controller;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.services.ICustomerService;
import com.ayi.trabajo_final.app.services.ITicketService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Api(value = "Ticket API", tags = {"Tickets Service"}) // Le decimos a Swagger que hay una transacción nueva, y que se llama Person Service (Es lo que se ve en grande en el Swagger)
@Slf4j // Esto es para el logeo
@RequestMapping(value = "/tickets", produces = {MediaType.APPLICATION_JSON_VALUE}) // Le indica al mundo externo que hay un servicio en la dirección web tal que se llama /persons, y lo que tiene que enviar es un JSON
@RestController // Esto es un controlador REST. @Controller es para un controlador MVC, no es REST
public class TicketController {

    @Autowired
    private ITicketService ticketService; // Acá traigo la interfaz del servicio

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
        try {
            ticketResponseDTO = ticketService.addTicket(ticketDTO);
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

}
