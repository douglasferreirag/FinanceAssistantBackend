package com.douglasfg.FinanceAssistantBackend.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByCategory;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.DashboardExpenseByMonthAndYear;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  
    @Query("""
       SELECT COALESCE(SUM(e.cost), 0)
       FROM Expense e
       WHERE MONTH(e.expenseDate) = :month
       AND YEAR(e.expenseDate) = :year
       """)
    Double findTotalExpensesByMonth( // para analise selecionada
            @Param("month") Integer month,
            @Param("year") Integer year);

    // Agrupamento por categoria (para gráficos)
    @Query("SELECT e.category.name, SUM(e.cost) FROM Expense e GROUP BY e.category.name")
    List<Object[]> getExpensesGroupedByCategory();

       // Busca todas as despesas já com categoria e pessoa carregadas = Expenselist
       @Query("SELECT DISTINCT e FROM Expense e JOIN FETCH e.category JOIN FETCH e.person")
       List<Expense> findAllWithCategoryAndPerson(); // Parece que também está sendo uusando no analysehistory

    // Busca todas as despesas de uma pessoa com categoria carregada
    @Query("SELECT DISTINCT e FROM Expense e " +
           "JOIN FETCH e.category c " +
           "JOIN FETCH e.person p " +
           "WHERE p.id = :personId")
    List<Expense> findAllByPersonWithCategory(@Param("personId") Long personId);


       // para o dashboard

       @Query("""
       SELECT COALESCE(SUM(e.cost),0)
       FROM Expense e
       """)
       BigDecimal getTotalExpenses();


       @Query("""
       SELECT COUNT(e)
       FROM Expense e
       """)
       BigDecimal getCountOfExpenses();

       @Query("""
       SELECT MAX(e.cost)
       FROM Expense e
       """)
       BigDecimal getHighestExpense();

       @Query("""
       SELECT AVG(e.cost)
       FROM Expense e
       """)
       BigDecimal getAverageExpense();

       
    @Query("""
        SELECT c.name, SUM(e.cost)
        FROM Expense e
        JOIN e.category c
        GROUP BY c.name
        ORDER BY SUM(e.cost) DESC
        """)
    List<DashboardExpenseByCategory> findExpensesByCategories();

    @Query(value = """
    SELECT
        MONTH(expense_date) as month,
        YEAR(expense_date) as year,
        SUM(cost) as totalExpenses
    FROM expenses
    GROUP BY
        YEAR(expense_date),
        MONTH(expense_date)
    ORDER BY
        YEAR(expense_date),
        MONTH(expense_date)
    """,
    nativeQuery = true)
    List<DashboardExpenseByMonthAndYear> findExpensesByMonthAndYear();  

}
