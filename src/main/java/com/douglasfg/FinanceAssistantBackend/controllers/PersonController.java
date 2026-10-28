package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.PersonRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.PersonResponse;
import com.douglasfg.FinanceAssistantBackend.services.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // Criar pessoa → 201 Created
    @PostMapping("/save")
    public ResponseEntity<PersonResponse> save(@RequestBody PersonRequest request) {
     
        PersonResponse saved = personService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Buscar todas → 200 OK (ou 204 No Content se vazio)
    @GetMapping("/findAll")
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<PersonResponse> people = personService.findAll();
        if (people.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(people);
    }

    // Buscar id por nome
    @GetMapping("/findIdByName/{name}")
    public ResponseEntity<PersonResponse> findIdByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findIdByName(name));
    }

    // Retornar apenas os nomes
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(personService.getAllNames());
    }
}
