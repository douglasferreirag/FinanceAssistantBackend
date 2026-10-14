package com.douglasfg.FinanceAssistantBackend.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dtos.responses.PersonResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.mappers.PersonMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonMapper personMapper;

    public PersonResponse prepareResponse(Person person) {
        return personMapper.toResponse(person);
    }

    public List<PersonResponse> prepareResponseList(List<Person> persons) {
        return persons.stream()
                .map(personMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PersonMapper getPersonMapper() {
        return personMapper;
    }
}
