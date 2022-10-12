package com.ayi.trabajo_final.app.mapper.impl;

import com.ayi.trabajo_final.app.dto.requests.TicketDTO;
import com.ayi.trabajo_final.app.dto.responses.TicketResponseDTO;
import com.ayi.trabajo_final.app.entities.TicketEntity;
import com.ayi.trabajo_final.app.mapper.ITicketMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TicketMapperImpl extends Exception implements ITicketMapper {

    private final ModelMapper modelMapper;

    @Override
    public TicketResponseDTO entityToDto(TicketEntity entity) {

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        modelMapper.map(entity, ticketResponseDTO);
        return ticketResponseDTO;
    }
    @Override
    public TicketEntity dtoToEntity(TicketDTO dto) {
        TicketEntity ticketEntity = new TicketEntity();
        modelMapper.map(dto, ticketEntity);
        return ticketEntity;
    }

}
