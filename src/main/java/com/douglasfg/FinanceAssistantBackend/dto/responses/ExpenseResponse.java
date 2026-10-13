package com.douglasfg.FinanceAssistantBackend.dto.responses;

import java.time.LocalDate;

import lombok.Data;


@Data

public class ExpenseResponse {
    private Long id;
    private String description;
    private Double cost;
    private LocalDate expenseDate;
}