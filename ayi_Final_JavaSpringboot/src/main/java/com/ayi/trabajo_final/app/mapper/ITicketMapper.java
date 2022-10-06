package com.ayi.trabajo_final.app.mapper;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.entities.TicketEntity;

public interface ITicketMapper {
    TicketResponseDTO entityToDto(TicketEntity entity);

    TicketEntity dtoToEntity(TicketDTO dto);
}
