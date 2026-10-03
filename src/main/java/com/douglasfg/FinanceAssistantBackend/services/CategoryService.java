package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.repositories.CategoryRepository;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class CategoryService {

    
    private final CategoryRepository categoryRepository;


  
    public Category save(Category category) {
       return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public Category findByName(String name) {
        return categoryRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

     public List<String> getAllNames() {
        return categoryRepository.findAllNames();
    }

    


}
