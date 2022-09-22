package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;

import java.util.List;

public interface IPersonService  {

    public List<PersonResponseDTO> findAllPersons();
}
