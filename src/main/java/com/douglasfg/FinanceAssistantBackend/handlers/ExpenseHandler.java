package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.entities.Person;
import com.douglasfg.FinanceAssistantBackend.mappers.ExpenseMapper;

import lombok.Data;


@Component
@Data
public class ExpenseHandler {

    private final ExpenseMapper expenseMapper;

    public Expense prepareExpense(ExpenseRequest request, 
                                Person person, 
                                Category category, 
                                Goal goal) {
        Expense expense = expenseMapper.toEntity(request);
        expense.setPerson(person);
        expense.setCategory(category);
        expense.setGoal(goal);
        return expense;
    }

    public ExpenseResponse prepareResponse(Expense expense) {
        return expenseMapper.toResponse(expense);
    }

    
}
