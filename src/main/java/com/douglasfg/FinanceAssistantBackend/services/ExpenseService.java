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
        Person person = personRepository.findByName(request.getPersonName())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found: " + request.getPersonName()));

        Category category = categoryRepository.findByName(request.getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + request.getCategoryName()));

        LocalDate expenseDate = request.getExpenseDate();
        Goal goal = goalRepository.findByMonthAndYear(expenseDate.getMonthValue(), expenseDate.getYear())
                .orElse(null);

        Expense expense = Expense.builder()
            .description(request.getDescription())
            .cost(request.getCost())
            .expenseDate(expenseDate)
            .person(person)
            .category(category)   // ✅ agora é 1x1
            .goal(goal)
            .build();

        Expense saved = expenseRepository.save(expense);
        return expenseHandler.getExpenseMapper().toResponse(saved);
    }




    // ✅ Listar todos os gastos → retorna DTO
    public List<ExpenseResponse> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseHandler.getExpenseMapper()::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Buscar gasto por ID → retorna DTO
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

        List<Expense> expenses = expenseRepository.findAllWithCategories();

        return aiAnalyzeHistoryService.analyzeHistoryExpenses(expenses);
        
    }

    
    public String analyzeHistoryByPerson(Long personId) {
        // Busca todas as despesas da pessoa com suas categorias
        List<Expense> expenses = expenseRepository.findAllByPersonWithCategories(personId);

        // Chama o serviço de análise (pode ser IA ou regra de negócio)
        return aiAnalyzeHistoryService.analyzeHistoryExpenses(expenses);

    }


}