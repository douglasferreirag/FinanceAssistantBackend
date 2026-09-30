package com.douglasfg.FinanceAssistantBackend.services;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpenseRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class ExpenseService {

    
    private final ExpenseRepository expenseRepository;

    private final AiAnalyzeHistoryService aiAnalyzeHistoryService;


   
    public Expense save(Expense expense) {
            // se expense tem um objeto Category
        return expenseRepository.save(expense);

    }
    // ✅ Listar todos os gastos
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    // ✅ Buscar gasto por ID
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Double sumByMonthAndYear(int month, int year) {
        Double total = expenseRepository.sumByMonthAndYear(month, year);
        return total != null ? total : 0.0;
    }

    
    public Map<String, Double> getExpensesGroupedByCategory() {
    return expenseRepository.getExpensesGroupedByCategory()
        .stream()
        .collect(Collectors.toMap(
            row -> (String) row[0],
            row -> (Double) row[1]
        ));
    }

    public String analyzeHistory() {

        List<Expense> expenses = expenseRepository.findAllWithCategories();

        return aiAnalyzeHistoryService.analyzeHistoryExpenses(expenses);
        
    }

   
} 