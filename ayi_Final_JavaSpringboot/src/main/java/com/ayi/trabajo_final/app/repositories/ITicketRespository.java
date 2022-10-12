package com.ayi.trabajo_final.app.repositories;

import com.ayi.trabajo_final.app.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRespository extends JpaRepository<TicketEntity, Long> {

/*    List<TicketEntity> findByCustomerEntity_Id (Long id);*/

}
