package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;

import java.util.List;

public interface IPersonService  {

    List<PersonResponseDTO> findAllPersons();

    PersonResponseDTO findPersonById(Long idPerson);

    // Este lo hice yo, ver después si funciona
    List<PersonResponseDTO> findPersonByName(String firstName, String lastName);

}
