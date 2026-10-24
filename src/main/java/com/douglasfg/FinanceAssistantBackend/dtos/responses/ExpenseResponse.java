package com.douglasfg.FinanceAssistantBackend.dtos.responses;

import java.time.LocalDate;

import lombok.Data;


@Data

public class ExpenseResponse {
    private Long id;
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    private String personId;
    private String categoryId;
    private Long goalId;   
}