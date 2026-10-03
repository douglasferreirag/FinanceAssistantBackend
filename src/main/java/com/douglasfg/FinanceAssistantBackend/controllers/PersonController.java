package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.services.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // Criar pessoa → 201 Created
    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person saved = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Buscar todas → 200 OK (ou 204 No Content se vazio)
    @GetMapping("/findAll")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personService.findAll();
        if (people.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(people);
    }

    // Buscar por nome
    @GetMapping("/findByName/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

    // Retornar apenas os nomes
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(personService.getAllNames());
    }
}
