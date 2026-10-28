package com.douglasfg.FinanceAssistantBackend.repositories;





import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.entities.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Query("SELECT c.id FROM Category c WHERE c.name = :name")
    Optional<Long> findIdByName(@Param("name") String name);

    @Query("SELECT c.name FROM Category c")
    List<String> findAllNames();

    
}
