package com.ayi.trabajo_final.app.repositories;
/**
 * Customer Repository
 * @CustomerEntity
 *
 * @Query getCutomerByName (String Name, String lastname)
 * @Query getCustomerByDNI (Integer dni)
 *
 */
import com.ayi.trabajo_final.app.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("Select PE from CustomerEntity PE where PE.firstName = :name and PE.lastName = :ape") // PE es un alias, dame todos los registros que tengan el name = **** y el apellido = ***
    List<CustomerEntity> getCustomerByName(@Param("name") String name, @Param("ape") String ape); // Una lista, porque si el apellido es Garcia pueden haber repetidos

    @Query("Select P from CustomerEntity P where P.documentNumber = :dni")
    Optional<CustomerEntity> getCustomerByDNI(@Param("dni") Integer dni);
}
