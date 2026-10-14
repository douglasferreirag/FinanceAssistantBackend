package com.douglasfg.FinanceAssistantBackend.dtos.responses;

import lombok.Data;

@Data
public class GoalResponse {
    private Long id;
    private int month;
    private int year;
    private Double ceiling;
}
