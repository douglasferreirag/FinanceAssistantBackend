package com.douglasfg.FinanceAssistantBackend.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DashboardSummaryResponse {

    private  double totalExpenses;
    private  double highestExpense;
    private double averageExpense;
    private Long totalRecords;
    
}