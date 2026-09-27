package com.douglasfg.FinanceAssistantBackend.repositories;





import com.douglasfg.FinanceAssistantBackend.entities.Expense;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  /*  @Query("SELECT COALESCE(SUM(e.cost), 0) FROM Expense e WHERE MONTH(e.expenseDate) = :month AND YEAR(e.expenseDate) = :year")
    Double sumByMonthAndYear(int month, int year); */ 

    @Query("SELECT COALESCE(SUM(e.cost), 0) " +
       "FROM ExpensePerson ep " +
       "JOIN ep.expense e " +
       "JOIN ep.person p " +
       "WHERE FUNCTION('MONTH', e.expenseDate) = :month " +
       "AND FUNCTION('YEAR', e.expenseDate) = :year " +
       "AND p.id = :personId")
    Double sumByMonthAndYear(int month, int year, Long personId);



    @Query("SELECT e.category.name, SUM(e.cost) FROM Expense e GROUP BY e.category.name")
    List<Object[]> getExpensesGroupedByCategory();


    @Query("SELECT e FROM ExpensePerson ep " +
       "JOIN ep.expense e " +
       "JOIN ep.person p " +
       "WHERE p.id = :personId " +
       "ORDER BY e.expenseDate DESC")
      List<Expense> getExpensesFromPerson(Long personId);

   

}
