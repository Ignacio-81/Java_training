package com.ayi.trabajo_final.app.controller;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.ayi.trabajo_final.app.dto.responses.AddressResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerDetailResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;
import com.ayi.trabajo_final.app.services.IAddressService;
import com.ayi.trabajo_final.app.services.ICustomerDetailService;
import com.ayi.trabajo_final.app.services.ICustomerService;
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
@Api(value = "Customer API", tags = {"Customers Service"}) // Le decimos a Swagger que hay una transacción nueva, y que se llama Person Service (Es lo que se ve en grande en el Swagger)
@Slf4j // Esto es para el logeo
@RequestMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE}) // Le indica al mundo externo que hay un servicio en la dirección web tal que se llama /persons, y lo que tiene que enviar es un JSON
@RestController // Esto es un controlador REST. @Controller es para un controlador MVC, no es REST
public class CustomerController {

    @Autowired
    private ICustomerService customerService; // Acá traigo la interfaz del servicio
    @Autowired
    private ICustomerDetailService iCustomerDetailService;
    @Autowired
    private IAddressService iAddressService;

    @PostMapping(
            value = "/addCustomer"
    )
    @ApiOperation(
            value = "POST a new value on the Database",
            httpMethod = "POST",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, // la respuesta es CREATED
                    message = "New Customer created"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error")
    })
    public ResponseEntity<?> postCustomer(
            @ApiParam(name = "CustomerDTO", value = "Payload data to create the new Customer")
            @RequestBody CustomerDTO customerDTO) {
        log.info("Entering add Customer [request]: {}", customerDTO);

        Map<String, Object> response = new HashMap<>();
        CustomerResponseDTO customerResponseDTO = null;
        AddressResponseDTO addressResponseDTO = null;
        CustomerDetailResponseDTO customerDetailResponseDTO = null;
        try {
            customerResponseDTO = customerService.addCustomer(customerDTO);
            customerDetailResponseDTO = iCustomerDetailService.addCustomerDetail(customerDTO.getCustomerDetailDTO(), customerResponseDTO);
            addressResponseDTO = iAddressService.addAddress(customerDTO.getAddressDTO(), customerResponseDTO);
            customerResponseDTO.setAddressResponseDTO(addressResponseDTO);
            customerResponseDTO.setCustomerDetailResponseDTO(customerDetailResponseDTO);
        } catch (ReadAccessException e) {
            response.put("Mensaje", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            //response.put("Mensaje", e.getMessage());
            response.put("Mensaje", "Error del sistema, ponganse en contanto con el administrador");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataBaseException e) {
            response.put("Mensaje", "Prueba de error database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        log.info("Leaving addCustomer [response]: {}", CustomerResponseDTO.builder().build().getIdCustomer());
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);


    }

}

