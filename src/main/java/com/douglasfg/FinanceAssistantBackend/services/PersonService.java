package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.PersonRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.PersonResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.handlers.PersonHandler;
import com.douglasfg.FinanceAssistantBackend.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonHandler personHandler;

    // ✅ Criar pessoa
    public PersonResponse save(PersonRequest request) {
        Person person = personHandler.getPersonMapper().toEntity(request);
        Person saved = personRepository.save(person);
        return personHandler.prepareResponse(saved);
    }

    // ✅ Listar todas as pessoas
    public List<PersonResponse> findAll() {
        List<Person> persons = personRepository.findAll();
        return personHandler.prepareResponseList(persons);
    }

    // ✅ Buscar pessoa por nome
    public PersonResponse findByName(String name) {
    Person person = personRepository.findByName(name)
                                        .orElseThrow(() -> new RuntimeException("Not found"));
        return personHandler.prepareResponse(person);
    }

    // ✅ Listar apenas os nomes
    public List<String> getAllNames() {
        return personRepository.findAll()
                               .stream()
                               .map(Person::getName)
                               .toList();
    }
}
