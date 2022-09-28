package com.ayi.curso.rest.serv.app.repositories;

import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    // Quiero hacer una query que no está entre las default
    @Query("Select PE from PersonEntity PE where PE.firstName = :name and PE.lastName = :ape") // PE es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    List<PersonEntity> getPersonByName(@Param("name") String name, @Param("ape") String ape); // Una lista, porque si el apellido es Garcia pueden haber repetidos

    @Modifying
    @Query("UPDATE PersonEntity P SET P.numberDocument = :dni , P.typeDocument = :t_doc, P.firstName=:nombre," +
            "P.lastName=:ape, P.dateBorn=:fec_nac WHERE P.idPerson = :id") // P es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    Integer putPersonById(@Param("id") Long id, @Param("dni") Integer dni, @Param("t_doc") String t_doc,
    @Param("nombre") String nom, @Param("ape") String ape, @Param ("fec_nac") LocalDate fec_nac);
}
