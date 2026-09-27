package com.douglasfg.FinanceAssistantBackend.services;

import com.douglasfg.FinanceAssistantBackend.entities.GoalExpense;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalExpenseService {

    private final GoalExpenseRepository goalExpenseRepository;

    public GoalExpense save(GoalExpense goalExpense) {
        return goalExpenseRepository.save(goalExpense);
    }

    public List<GoalExpense> findByGoal(Long goalId) {
        return goalExpenseRepository.findByGoalId(goalId);
    }

    public List<GoalExpense> findByExpense(Long expenseId) {
        return goalExpenseRepository.findByExpenseId(expenseId);
    }

    public List<GoalExpense> findAll() {
        return goalExpenseRepository.findAll();
    }
}
