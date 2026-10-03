package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.services.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // Criar despesa → 201 Created ou 400 Bad Request
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Expense expense) {
        try {
            
            Expense saved = expenseService.save(expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }

    // Buscar todas → 200 OK ou 204 No Content
    @GetMapping("/findAll")
    public ResponseEntity<List<Expense>> findAll() {
        List<Expense> expenses = expenseService.findAll();
        if (expenses.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(expenses); // 200 OK
    }

    // Buscar por ID → 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id) {
        return expenseService.findById(id)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }

    @GetMapping("/sumByMonthAndYear")
    public ResponseEntity<Double> sumByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year
        ) {
        Double result = expenseService.sumByMonthAndYear(month, year);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/analyzeHistory")
    public String analyzeHistory() {
        return expenseService.analyzeHistory();
    }

    @PostMapping("/person/{personId}")
    public String analyzeHistoryByPerson(@PathVariable Long personId) {
        return expenseService.analyzeHistoryByPerson(personId);
    }

}