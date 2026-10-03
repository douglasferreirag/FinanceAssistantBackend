package com.douglasfg.FinanceAssistantBackend.repositories;





import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglasfg.FinanceAssistantBackend.entities.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(String name);

    @Query("SELECT c.name FROM Category c")
    List<String> findAllNames();

    
}
