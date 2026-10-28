package com.douglasfg.FinanceAssistantBackend.repositories;






import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;



public interface GoalRepository extends JpaRepository<Goal, Long> {

    // Outra variação: retorna a entidade Goal inteira
    @Query("SELECT g.id FROM Goal g WHERE g.month = :month AND g.year = :year")
    Optional<Long> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

}