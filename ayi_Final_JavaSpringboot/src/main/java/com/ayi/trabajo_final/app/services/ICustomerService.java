package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.CustomerDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

import java.io.IOException;
import java.util.List;

public interface ICustomerService {


    public CustomerResponseDTO addCustomer(CustomerDTO customer) throws ReadAccessException, DataBaseException;

    CustomerResponseDTO findCustomerById(Long idCustomer) throws ReadAccessException;

    void removeCustomerById(Long idCustomer) throws ReadAccessException;

    CustomerResponseDTO updateCustomerById(Long idCustomer, CustomerDTO customerDTO) throws ReadAccessException;

    List<CustomerTicketsResponseDTO> findAllTicketByCustomerById(Long idCustomer) throws ReadAccessException;

}
