package com.douglasfg.FinanceAssistantBackend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseDTO {
    private String description;
    private Double cost;
    private LocalDate date;
    private String category;
    
}
