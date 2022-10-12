package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.CustomerTicketsResponseDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

import java.util.List;

public interface ITicketService {

    TicketResponseDTO addTicket(TicketDTO ticketDTO, CustomerResponseDTO customerRDTO) throws ReadAccessException, DataBaseException;

    TicketResponseDTO findTicketById(Long idTicket) throws ReadAccessException;

    void removeTicketsById(Long idTicket) throws ReadAccessException;

    TicketResponseDTO updateTicketById(Long idTicket, TicketDTO ticketDTO) throws ReadAccessException;

    List<CustomerTicketsResponseDTO> findAllTicketByCustomerById(Long idCustomer) throws ReadAccessException;
}
