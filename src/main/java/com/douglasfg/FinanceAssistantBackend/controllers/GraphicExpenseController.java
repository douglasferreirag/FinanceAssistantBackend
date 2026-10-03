package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.services.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense-graphic")
@RequiredArgsConstructor
public class GraphicExpenseController {

    private final ExpenseService expenseService;

    // Buscar gastos agrupados por categoria → 200 OK ou 204 No Content
    @GetMapping("/expenses-by-category")
    public ResponseEntity<Map<String, Double>> getExpensesByCategory() {
        Map<String, Double> result = expenseService.getExpensesGroupedByCategory();

        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(result); // 200 OK
    }
}
