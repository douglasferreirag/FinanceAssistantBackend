package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dtos.ai.ExpenseAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.mappers.ExpenseAnalyzeDataMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpenseAnalyzeDataHandler {

    private final ExpenseAnalyzeDataMapper expenseAnalyzeDataMapper;

    public ExpenseAnalyzeData prepareData(
            Expense expense
    ) {

        return expenseAnalyzeDataMapper.toData(
                expense
        );

    }

}