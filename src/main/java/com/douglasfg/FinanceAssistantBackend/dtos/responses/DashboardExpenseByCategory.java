package com.douglasfg.FinanceAssistantBackend.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DashboardExpenseByCategory {

    private  double totalExpenses;
    private  String categoryName;
   
    
}