package com.douglasfg.FinanceAssistantBackend.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private Long id;
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    private String personName;
    private String goalName;
    private String categoryName;
}