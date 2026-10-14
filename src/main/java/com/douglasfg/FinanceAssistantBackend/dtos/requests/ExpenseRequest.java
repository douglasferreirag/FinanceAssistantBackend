package com.douglasfg.FinanceAssistantBackend.dtos.requests;

import java.time.LocalDate;

import lombok.Data;




@Data
public class ExpenseRequest {
  
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    private String personName;
    private String categoryName;
}