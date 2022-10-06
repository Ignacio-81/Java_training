package com.ayi.trabajo_final.app.services;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.exceptions.DataBaseException;
import com.ayi.trabajo_final.app.exceptions.ReadAccessException;

public interface ITicketService {
    TicketResponseDTO addTicket(TicketDTO ticketDTO) throws ReadAccessException, DataBaseException;
}
