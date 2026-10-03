package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    public List<String> getAllNames() {
        return personRepository.findAll()
                               .stream()
                               .map(Person::getName)
                               .toList();
    }
}
