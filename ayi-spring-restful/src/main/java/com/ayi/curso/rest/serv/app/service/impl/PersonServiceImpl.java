package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repositories.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service //Indica que es un servicio y puede ser inyectado
@Slf4j
@Transactional //Maneja la transaccion, hace el commit y maneja el rollback begin , commit, rollback

public class PersonServiceImpl implements IPersonService {

    @Autowired // Le digo que es una "tubería", genera un puente entre una cosa y otra para traer o enviar info
    private IPersonRepository personRepository; // Fijate que no hicimos implementación de IPersonRepository, ya con esto es suficiente

    @Autowired
    private IPersonMapper personMapper; // Acá uso los mapper (me transforma una entidad a otra)

    @Override
    public List<PersonResponseDTO> findAllPersons() { // Me devuelve todas las personas de la tabla

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        personResponseDTOs = personEntities.stream()
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateBorn(),
                        lt.getDateCreated(),
                        lt.getDateModified() // Tdo esto es lo que estoy enviando al constructor de PersonResponseDTO
                ))
                .collect(Collectors.toList()); // A través de stream, mapeo los campos de personEntities a personResponseDTOs

        return personResponseDTOs;
    }

    @Override
    public PersonResponseDTO findPersonById(Long idPerson) {
        PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        personResponseDTO = personMapper.entityToDto(entity.get());
        return personResponseDTO;
    }

    @Override // Este lo hice yo, ver después si funciona. Funciona, muestra una lista vacía si no encuentra nada
    public List<PersonResponseDTO> findPersonByName(String firstName, String lastName) {
        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.getPersonByName(firstName, lastName);

        personResponseDTOs = personEntities.stream() // Acá voy agregando a la lista todas las filas que encuentra con el nombre y apellido que brinde como parámetro
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateBorn(),
                        lt.getDateCreated(),
                        lt.getDateModified()
                ))
                .collect(Collectors.toList());

        return personResponseDTOs;
    }

    @Override
    public void removePersonById(Long idPerson) {
        //PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson); // Ya tengo todos los métodos para buscar, deletear, etc

        if (!entity.isPresent()) {
            throw new RuntimeException("Error no existe el id de persona buscado");
        }

        personRepository.deleteById(idPerson);
        //personResponseDTO = personMapper.entityToDto(entity.get());
        //return personResponseDTO;

    }

    @Override
    public PersonResponseDTO addPerson(PersonDTO persona) {
        PersonResponseDTO personResponseDTO;

        PersonEntity entity = new PersonEntity(
                persona.getFirstName(),
                persona.getLastName(),
                persona.getTypeDocument(),
                persona.getNumberDocument(),
                persona.getDateBorn()
        );

        personRepository.save(entity);

        personResponseDTO = personMapper.entityToDto(entity);
        return personResponseDTO;

    }
    @Override
    public PersonResponseDTOFull findAllPersonsForPage(Integer page , Integer size) { // Me devuelve todas las personas de la tabla

        PersonResponseDTOFull personResponseDTOFull;
        List<PersonResponseDTO> personResponseDTOs;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        Page<PersonEntity> personEntities = personRepository.findAll(pageable);




        return personResponseDTOs;
    }
}
