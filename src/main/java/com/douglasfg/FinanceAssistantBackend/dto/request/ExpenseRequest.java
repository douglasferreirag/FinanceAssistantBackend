package com.douglasfg.FinanceAssistantBackend.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
  
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    private String personName;
    private String categoryName;
}