package com.douglasfg.FinanceAssistantBackend.repositories;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  
      @Query("SELECT COALESCE(SUM(e.cost), 0) " +
            "FROM Expense e " +
            "JOIN e.person p " +
            "WHERE MONTH(e.expenseDate) = :month " +
            "AND YEAR(e.expenseDate) = :year")
      Double sumByMonthAndYear(int month, int year); // SOMATÓRIO GERAL PARA ANÁLISE DE GASTOS SEM iA


      @Query("SELECT c.name, SUM(e.cost) " +
            "FROM Expense e " +
            "JOIN e.categories c " +
            "GROUP BY c.name")
            List<Object[]> getExpensesGroupedByCategory(); // Para gráfico (GraphicExpense)


      @Query("SELECT DISTINCT e FROM Expense e " +
            "JOIN FETCH e.categories")
      List<Expense> findAllWithCategories(); // Para analise geral dos gastos utilizando ia.


      @Query("SELECT DISTINCT e FROM Expense e " +
            "JOIN FETCH e.categories c " +
            "JOIN FETCH e.person p " +
            "WHERE p.id = :personId")
      List<Expense> findAllByPersonWithCategories(@Param("personId") Long personId); // Para analise individual junto da ia.



      

}
