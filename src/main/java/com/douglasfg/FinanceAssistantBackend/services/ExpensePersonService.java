package com.douglasfg.FinanceAssistantBackend.services;

import com.douglasfg.FinanceAssistantBackend.entities.ExpensePerson;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpensePersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensePersonService {

    private final ExpensePersonRepository expensePersonRepository;

    public ExpensePerson save(ExpensePerson expensePerson) {
        return expensePersonRepository.save(expensePerson);
    }

    public List<ExpensePerson> findAll() {
        return expensePersonRepository.findAll();
    }
}
