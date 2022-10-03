package com.ayi.trabajo_final.app.services;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.exception.DataBaseException;
import com.ayi.curso.rest.serv.app.exception.ReadAccessException;

import java.util.List;

public interface ICustomerService {

    List<PersonResponseDTO> findAllPersons() throws ReadAccessException;

    PersonResponseDTO findPersonById(Long idPerson) throws ReadAccessException;

    // Este lo hice yo, ver después si funciona
    List<PersonResponseDTO> findPersonByName(String firstName, String lastName);

    public void removePersonById(Long idPerson) throws ReadAccessException;

    public PersonResponseDTO addPerson(PersonDTO persona) throws ReadAccessException, DataBaseException;

    PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size) throws ReadAccessException;

    PersonResponseDTO updatePersonById(Long idPerson, PersonDTO personaDTO) throws ReadAccessException;

/*    PersonResponseDTO updatePerson(Long idPerson, PersonDTO request);*/
}
