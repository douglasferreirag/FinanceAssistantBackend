package com.douglasfg.FinanceAssistantBackend.controllers;

import com.douglasfg.FinanceAssistantBackend.entities.ExpensePerson;
import com.douglasfg.FinanceAssistantBackend.services.ExpensePersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-person")
@RequiredArgsConstructor
public class ExpensePersonController {

    private final ExpensePersonService expensePersonService;

    // Criar vínculo pessoa ↔ despesa
    @PostMapping
    public ResponseEntity<ExpensePerson> create(@RequestBody ExpensePerson expensePerson) {
        ExpensePerson saved = expensePersonService.save(expensePerson);
        return ResponseEntity.ok(saved);
    }

    // Listar todos os vínculos
    @GetMapping
    public ResponseEntity<List<ExpensePerson>> findAll() {
        return ResponseEntity.ok(expensePersonService.findAll());
    }
}
