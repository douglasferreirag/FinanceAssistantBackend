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

import com.douglasfg.FinanceAssistantBackend.dtos.requests.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardSummaryResponse;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByCategory;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByMonthAndYear;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.services.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    

    // Criar despesa → 201 Created ou 400 Bad Request
    @PostMapping("/save")
    public ResponseEntity<ExpenseResponse> save(@Valid @RequestBody ExpenseRequest request) {
        try {
            System.out.println(request.getPersonName());
            ExpenseResponse response = expenseService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // Buscar todas → 200 OK ou 204 No Content
    @GetMapping("/findAllWithCategoryAndPerson") // Expenselist
    public ResponseEntity<List<ExpenseResponse>> findAllWithCategoryAndPerson() {
        List<ExpenseResponse> expenses = expenseService.findAllWithCategoryAndPerson();
        if (expenses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(expenses);
    }

    // Buscar por ID → 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable Long id) {
        ExpenseResponse expense = expenseService.findById(id);
        return ResponseEntity.ok(expense); // 200 OK
    }

    @GetMapping("/TotalExpensesByMonth")
    public ResponseEntity<Double> getTotalExpensesByMonth(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                expenseService.getTotalExpensesByMonth(month, year)
        );
    }

    @PostMapping("/analyzeHistory")
    public String analyzeHistory() {
        return expenseService.analyzeHistory();
    }


    // Para o dashboard

    @GetMapping("/dashboardSummary")
    public DashboardSummaryResponse getDashboardSummary() {
        return expenseService.getDashboardSummary();
    }

    @GetMapping("/dashboardExpenseCategory")
    public ResponseEntity<List<DashboardExpenseByCategory>>
            getExpensesByCategory() {

        return ResponseEntity.ok(
                expenseService.getExpensesByCategories()
        );
    }

    @GetMapping("/dashboardExpensesByMonthAndYear")
    public ResponseEntity<List<DashboardExpenseByMonthAndYear>> getExpensesByMonthAndYear() {
        return ResponseEntity.ok(expenseService.getExpensesByMonthAndYear());
    }

}