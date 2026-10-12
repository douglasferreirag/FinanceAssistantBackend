package com.douglasfg.FinanceAssistantBackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dto.request.ExpenseRequest;
import com.douglasfg.FinanceAssistantBackend.dto.response.ExpenseResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    // Converte DTO de entrada para entidade
    @Mapping(target = "id", ignore = true)        // id é gerado pelo banco
    @Mapping(target = "person", ignore = true)    // setado no handler
    @Mapping(target = "category", ignore = true)  // setado no handler
    @Mapping(target = "goal", ignore = true)      // setado no handler
    Expense toEntity(ExpenseRequest request);

    // Converte entidade para DTO de saída (apenas dados principais)
    ExpenseResponse toResponse(Expense expense);
}
