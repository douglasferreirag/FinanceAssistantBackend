package com.douglasfg.FinanceAssistantBackend.dtos.responses;

import java.time.LocalDate;
import lombok.Data;


@Data

public class ExpenseResponse {
    private Long id;
    private String description;
    private Double cost;
    private LocalDate expenseDate;
    //private long personId;
    //private long categoryId;
    private String personName;
    private String categoryName;
    private long goalId;

}