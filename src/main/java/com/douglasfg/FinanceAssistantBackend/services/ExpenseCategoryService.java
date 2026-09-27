package com.douglasfg.FinanceAssistantBackend.services;

import com.douglasfg.FinanceAssistantBackend.entities.ExpenseCategory;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpenseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory save(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    public List<ExpenseCategory> findByExpense(Long expenseId) {
        return expenseCategoryRepository.findByExpenseId(expenseId);
    }

    public List<ExpenseCategory> findByCategory(Long categoryId) {
        return expenseCategoryRepository.findByCategoryId(categoryId);
    }

    public List<ExpenseCategory> findAll() {
        return expenseCategoryRepository.findAll();
    }
}
