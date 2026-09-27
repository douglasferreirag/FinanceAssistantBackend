package com.douglasfg.FinanceAssistantBackend.repositories;

import com.douglasfg.FinanceAssistantBackend.entities.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    List<ExpenseCategory> findByExpenseId(Long expenseId);

    List<ExpenseCategory> findByCategoryId(Long categoryId);
}
