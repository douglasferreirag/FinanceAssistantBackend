package com.douglasfg.FinanceAssistantBackend.dtos.responses;

import java.util.List;

import lombok.Data;

@Data
public class GoalResponse {
    private Long id;
    private int month;
    private int year;
    private Double ceiling;

    // Lista resumida de despesas associadas

    private List<ExpenseResponse> expenses;
}
