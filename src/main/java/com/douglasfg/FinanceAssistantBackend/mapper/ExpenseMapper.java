package com.douglasfg.FinanceAssistantBackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dto.request.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dto.response.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    // Converte DTO de entrada para entidade
    Expense toEntity(ExpenseRequest request);

    // Converte entidade para DTO de saída
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "goal.name", target = "goalName")
    @Mapping(source = "category.name", target = "categoryName")
    ExpenseResponse toResponse(Expense expense);
}
