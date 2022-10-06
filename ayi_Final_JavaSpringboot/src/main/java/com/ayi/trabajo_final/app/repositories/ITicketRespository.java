package com.ayi.trabajo_final.app.repositories;

import com.ayi.trabajo_final.app.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRespository extends JpaRepository<TicketEntity, Long> {

}
