package com.douglasfg.FinanceAssistantBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasfg.FinanceAssistantBackend.entities.GoalExpense;

public interface GoalExpenseRepository extends JpaRepository<GoalExpense, Long> {
    List<GoalExpense> findByGoalId(Long goalId);
    List<GoalExpense> findByExpenseId(Long expenseId);
}
