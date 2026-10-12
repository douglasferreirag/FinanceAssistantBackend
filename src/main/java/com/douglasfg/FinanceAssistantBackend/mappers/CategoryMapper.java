package com.douglasfg.FinanceAssistantBackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dto.request.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dto.response.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true) // id gerado pelo banco
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);
}
