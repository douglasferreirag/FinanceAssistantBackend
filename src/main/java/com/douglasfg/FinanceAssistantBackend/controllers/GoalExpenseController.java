package com.douglasfg.FinanceAssistantBackend.controllers;

import com.douglasfg.FinanceAssistantBackend.entities.GoalExpense;
import com.douglasfg.FinanceAssistantBackend.services.GoalExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals-expenses")
@RequiredArgsConstructor
public class GoalExpenseController {

    private final GoalExpenseService goalExpenseService;

    // Criar vínculo Goal ↔ Expense
    @PostMapping
    public ResponseEntity<GoalExpense> create(@RequestBody GoalExpense goalExpense) {
        return ResponseEntity.ok(goalExpenseService.save(goalExpense));
    }

    // Buscar vínculos por Goal
    @GetMapping("/goal/{goalId}")
    public ResponseEntity<List<GoalExpense>> getByGoal(@PathVariable Long goalId) {
        return ResponseEntity.ok(goalExpenseService.findByGoal(goalId));
    }

    // Buscar vínculos por Expense
    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<List<GoalExpense>> getByExpense(@PathVariable Long expenseId) {
        return ResponseEntity.ok(goalExpenseService.findByExpense(expenseId));
    }

    // Listar todos os vínculos
    @GetMapping
    public ResponseEntity<List<GoalExpense>> findAll() {
        return ResponseEntity.ok(goalExpenseService.findAll());
    }
}
