package com.douglasfg.FinanceAssistantBackend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceNotFoundException;
import com.douglasfg.FinanceAssistantBackend.handlers.ExpenseHandler;
import com.douglasfg.FinanceAssistantBackend.repositories.CategoryRepository;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpenseRepository;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;
import com.douglasfg.FinanceAssistantBackend.repositories.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final PersonRepository personRepository;
    private final GoalRepository goalRepository;
    private final CategoryRepository categoryRepository;
    private final AiAnalyzeHistoryService aiAnalyzeHistoryService;
    private final ExpenseHandler expenseHandler;

    @Transactional
    public ExpenseResponse save(ExpenseRequest request) {
        // 🔹 Busca pessoa
        Person person = personRepository.findByName(request.getPersonName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found: " + request.getPersonName()));

        // 🔹 Busca categoria
        Category category = categoryRepository.findByName(request.getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found: " + request.getCategoryName()));

        // 🔹 Normaliza data e associa meta automaticamente
        LocalDate expenseDate = request.getExpenseDate();
        Goal goal = goalRepository.findByMonthAndYear(
                expenseDate.getMonthValue(),
                expenseDate.getYear()
        ).orElse(null);

        // 🔹 Cria entidade de gasto
        Expense expense = Expense.builder()
                .description(request.getDescription().trim())
                .cost(request.getCost())
                .expenseDate(expenseDate)
                .person(person)
                .category(category)
                .goal(goal)
                .build();

        // 🔹 Persiste e retorna response
        Expense saved = expenseRepository.save(expense);
        return expenseHandler.getExpenseMapper().toResponse(saved);
    }


    public List<ExpenseResponse> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseHandler.getExpenseMapper()::toResponse)
                .collect(Collectors.toList());
    }

    public ExpenseResponse findById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));
        return expenseHandler.getExpenseMapper().toResponse(expense);
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
        List<Expense> expenses = expenseRepository.findAllWithCategory();
        return aiAnalyzeHistoryService.analyzeHistoryExpenses(expenses);
    }

    public String analyzeHistoryByPerson(Long personId) {
        List<Expense> expenses = expenseRepository.findAllByPersonWithCategory(personId);
        return aiAnalyzeHistoryService.analyzeHistoryExpenses(expenses);
    }
}
