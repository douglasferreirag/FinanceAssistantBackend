
package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dto.requests.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dto.responses.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.mappers.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryMapper categoryMapper;

    // Prepara entidade a partir do DTO de entrada
    public Category prepareCategory(CategoryRequest request) {
        return categoryMapper.toEntity(request);
    }

    // Converte entidade para DTO de saída
    public CategoryResponse prepareResponse(Category category) {
        return categoryMapper.toResponse(category);
    }
}
