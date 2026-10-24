package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Category;


@Mapper(componentModel = "spring", uses = {ExpenseMapper.class})
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true) // id gerado pelo banco
    @Mapping(target = "name", ignore = false) // name gerado pelo banco
    @Mapping(target = "expenses", ignore = true) // expenses gerado pelo banco
    Category toEntity(CategoryRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "expenses", target = "expenses")
    CategoryResponse toResponse(Category category);
}
