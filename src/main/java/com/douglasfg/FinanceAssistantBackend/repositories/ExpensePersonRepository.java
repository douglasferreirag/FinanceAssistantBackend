package com.douglasfg.FinanceAssistantBackend.repositories;

import com.douglasfg.FinanceAssistantBackend.entities.ExpensePerson;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpensePersonRepository extends JpaRepository<ExpensePerson, Long> {

}
