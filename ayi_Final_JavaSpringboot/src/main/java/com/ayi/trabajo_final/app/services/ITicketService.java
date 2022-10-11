package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

public interface ITicketService {
    public TicketResponseDTO addTicket(TicketDTO ticketDTO) throws ReadAccessException, DataBaseException;

    TicketResponseDTO findTicketById(Long idTicket) throws ReadAccessException;

    void removeTicketsById(Long idTicket) throws ReadAccessException;

    TicketResponseDTO updateTicketById(Long idTicket, TicketDTO ticketDTO) throws ReadAccessException;
}
