package com.douglasfg.FinanceAssistantBackend.dtos.responses;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {
    
    private Long id;

    private String name;

    private List<ExpenseResponse> expenses;
}
