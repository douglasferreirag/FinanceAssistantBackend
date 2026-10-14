package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true) // id gerado pelo banco
    @Mapping(target = "name", ignore = true) // id gerado pelo banco
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);
}
