package com.ayi.curso.rest.serv.app.repositories;

import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    // Quiero hacer una query que no está entre las default
    @Query("Select PE from PersonEntity PE where PE.firstName = :name and PE.lastName = :ape") // PE es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    List<PersonEntity> getPersonByName(@Param("name") String name, @Param("ape") String ape); // Una lista, porque si el apellido es Garcia pueden haber repetidos
}
