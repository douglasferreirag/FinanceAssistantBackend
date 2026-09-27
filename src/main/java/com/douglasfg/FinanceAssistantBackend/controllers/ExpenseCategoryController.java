package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.entities.ExpenseCategory;
import com.douglasfg.FinanceAssistantBackend.services.ExpenseCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense-category")
@RequiredArgsConstructor
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    // Criar vínculo Expense ↔ Category
    @PostMapping
    public ResponseEntity<ExpenseCategory> create(@RequestBody ExpenseCategory expenseCategory) {
        ExpenseCategory saved = expenseCategoryService.save(expenseCategory);
        return ResponseEntity.ok(saved);
    }

    // Buscar vínculos por Expense
    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<List<ExpenseCategory>> getByExpense(@PathVariable Long expenseId) {
        return ResponseEntity.ok(expenseCategoryService.findByExpense(expenseId));
    }

    // Buscar vínculos por Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ExpenseCategory>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(expenseCategoryService.findByCategory(categoryId));
    }

    // Listar todos os vínculos
    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> findAll() {
        return ResponseEntity.ok(expenseCategoryService.findAll());
    }
}
