package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglasfg.FinanceAssistantBackend.dto.requests.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dto.responses.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceAlreadyExistsException;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceNotFoundException;
import com.douglasfg.FinanceAssistantBackend.handlers.CategoryHandler;
import com.douglasfg.FinanceAssistantBackend.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryHandler categoryHandler;

    @Transactional
    public CategoryResponse save(CategoryRequest request) {
        // Verifica se já existe categoria com esse nome
        categoryRepository.findByName(request.getName())
                .ifPresent(c -> {
                    throw new ResourceAlreadyExistsException("Categoria já existe: " + request.getName());
                });

        // Prepara entidade a partir do request
        Category category = categoryHandler.prepareCategory(request);

        // Salva no banco
        Category saved = categoryRepository.save(category);

        // Converte para resposta
        return categoryHandler.prepareResponse(saved);
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryHandler::prepareResponse)
                .toList();
    }

    public CategoryResponse findByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + name));
        return categoryHandler.prepareResponse(category);
    }

    public List<String> getAllNames() {
        return categoryRepository.findAllNames();
    }

  
}
