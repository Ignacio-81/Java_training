package com.ayi.trabajo_final.app.repositories;

import com.ayi.trabajo_final.app.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // Quiero hacer una query que no está entre las default
    @Query("Select PE from CustomerEntity PE where PE.firstName = :name and PE.lastName = :ape") // PE es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    List<CustomerEntity> getCustomerByName(@Param("name") String name, @Param("ape") String ape); // Una lista, porque si el apellido es Garcia pueden haber repetidos

/*    @Modifying
    @Query("UPDATE PersonEntity P SET P.numberDocument = :dni , P.typeDocument = :t_doc, P.firstName=:nombre," +
            "P.lastName=:ape, P.dateBorn=:fec_nac WHERE P.idPerson = :id") // P es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    Integer putPersonById(@Param("id") Long id, @Param("dni") Integer dni, @Param("t_doc") String t_doc,
    @Param("nombre") String nom, @Param("ape") String ape, @Param ("fec_nac") LocalDate fec_nac);*/

    @Query("Select P from CustomerEntity P where P.documentNumber = :dni")
    Optional<CustomerEntity> getCustomerByDNI(@Param("dni") Integer dni);
}
