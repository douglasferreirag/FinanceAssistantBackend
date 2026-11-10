package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.ai.ExpenseAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseAnalyzeDataMapper {

    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "category.name", target = "categoryName")
    ExpenseAnalyzeData toData(Expense expense);

}