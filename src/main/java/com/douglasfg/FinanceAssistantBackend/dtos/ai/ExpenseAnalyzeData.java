package com.douglasfg.FinanceAssistantBackend.dtos.ai;

import java.time.LocalDate;

import lombok.Data;




@Data
public class ExpenseAnalyzeData {
  
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    private String personName;
    private String categoryName;
    
}