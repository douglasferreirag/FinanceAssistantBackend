package com.douglasfg.FinanceAssistantBackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  
    // Somatório geral por mês/ano
    @Query("SELECT COALESCE(SUM(e.cost), 0) " +
           "FROM Expense e " +
           "JOIN e.person p " +
           "WHERE MONTH(e.expenseDate) = :month " +
           "AND YEAR(e.expenseDate) = :year")
    Double sumByMonthAndYear(@Param("month") int month, @Param("year") int year);

    // Agrupamento por categoria (para gráficos)
    @Query("SELECT e.category.name, SUM(e.cost) FROM Expense e GROUP BY e.category.name")
    List<Object[]> getExpensesGroupedByCategory();

    // Busca todas as despesas já com categoria carregada
    @Query("SELECT DISTINCT e FROM Expense e JOIN FETCH e.category")
    List<Expense> findAllWithCategory();

    // Busca todas as despesas de uma pessoa com categoria carregada
    @Query("SELECT DISTINCT e FROM Expense e " +
           "JOIN FETCH e.category c " +
           "JOIN FETCH e.person p " +
           "WHERE p.id = :personId")
    List<Expense> findAllByPersonWithCategory(@Param("personId") Long personId);
}
