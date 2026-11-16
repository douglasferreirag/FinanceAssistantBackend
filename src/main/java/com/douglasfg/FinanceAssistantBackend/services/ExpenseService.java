package com.douglasfg.FinanceAssistantBackend.services;


import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.dtos.ai.ExpenseAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.dtos.ai.GoalAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.dtos.requests.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByCategory;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByMonthAndYear;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardSummaryResponse;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceNotFoundException;
import com.douglasfg.FinanceAssistantBackend.handlers.ExpenseAnalyzeDataHandler;
import com.douglasfg.FinanceAssistantBackend.handlers.ExpenseHandler;
import com.douglasfg.FinanceAssistantBackend.handlers.GoalAnalyzeDataHandler;
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
    private final GoalAnalyzeDataHandler goalAnalyzeDataHandler;
    private final ExpenseAnalyzeDataHandler expenseAnalyzeDataHandler;

        // Salvar os dados no banco

        @Transactional
        public ExpenseResponse save(ExpenseRequest request) {
                // 🔹 Busca pessoa
                long personId = personRepository.findIdByName(request.getPersonName())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Person not found: " + request.getPersonName()));

                // 🔹 Busca categoria
                long categoryId = categoryRepository.findIdByName(request.getCategoryName())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Category not found: " + request.getCategoryName()));

                // 🔹 Normaliza data e associa meta automaticamente
                LocalDate expenseDate = request.getExpenseDate();
                Long goalId = goalRepository.findByMonthAndYear(
                request.getExpenseDate().getMonthValue(),
                request.getExpenseDate().getYear()
                ).orElse(null);

                // 🔹 Cria entidade de gasto
                Expense expense = Expense.builder()
                        .description(request.getDescription().trim())
                        .cost(request.getCost())
                        .expenseDate(expenseDate)
                        .person(Person.builder().id(personId).build())
                        .category(Category.builder().id(categoryId).build())
                        .goal(goalId != null ? Goal.builder().id(goalId).build() : null)
                        .build();

                // 🔹 Persiste e retorna response
                Expense saved = expenseRepository.save(expense);
                return expenseHandler.getExpenseMapper().toResponse(saved);
        }

        // Listar os dados  (Expenselist)

        public List<ExpenseResponse> listAll() {

                return expenseRepository
                        .findAllWithCategoryAndPerson()
                        .stream()
                        .map(expenseHandler::prepareResponse)
                        .toList();
        }

        // Encontrar por Id.

        public ExpenseResponse findById(Long id) {
                Expense expense = expenseRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));
                return expenseHandler.getExpenseMapper().toResponse(expense);
        }

  
        // Para análise de dados com ia

        // Geral

        public String analyzeHistory() {

                List<ExpenseAnalyzeData> expenses =
                        expenseRepository
                                .findAllWithCategoryAndPerson()
                                .stream()
                                .map(expenseAnalyzeDataHandler::prepareData)
                                .toList();

                List<GoalAnalyzeData> goals =
                        goalRepository
                                .findAll()
                                .stream()
                                .map(goalAnalyzeDataHandler::prepareData)
                                .toList();

                return aiAnalyzeHistoryService
                        .analyzeHistoryExpenses(
                                expenses,
                                goals
                        );
        }

        // Por pessoa

        public String analyzeHistoryByPerson(String personName) {

                List<ExpenseAnalyzeData> expenses =
                        expenseRepository
                                .findAllByPersonName(personName)
                                .stream()
                                .map(expenseAnalyzeDataHandler::prepareData)
                                .filter(expense ->
                                        expense.getPersonName()
                                                .equalsIgnoreCase(personName))
                                .toList();

                        List<GoalAnalyzeData> goals =
                                goalRepository
                                        .findAll()
                                        .stream()
                                        .map(goalAnalyzeDataHandler::prepareData)
                                        .toList();

                return aiAnalyzeHistoryService
                        .analyzeHistoryExpenses(
                                expenses,
                                goals
                        );
        }

        // Para o dashboard

        public DashboardSummaryResponse getDashboardSummary() {

                List<Expense> expenses = expenseRepository.findAll();

                Double totalExpenses = expenses.stream()
                .map(Expense::getCost)
                .reduce(0.0, Double::sum);

                Double highestExpense = expenses.stream()
                        .map(Expense::getCost)
                        .max(Double::compareTo)
                        .orElse(0.0);

                Double averageExpense = expenses.isEmpty()
                        ? 0.0
                        : totalExpenses / expenses.size();

                Long totalRecords = (long) expenses.size();

                return new DashboardSummaryResponse(
                        totalExpenses,
                        highestExpense,
                        averageExpense,
                        totalRecords
                );
        }

        @Transactional
        public List<DashboardExpenseByCategory>
                getExpensesByCategories() {

                return expenseRepository
                        .findExpensesByCategories();
        }

        @Transactional
        public List<DashboardExpenseByMonthAndYear>
                getExpensesByMonthAndYear() {

                return expenseRepository
                        .findExpensesByMonthAndYear();
        }



}
