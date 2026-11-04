package com.douglasfg.FinanceAssistantBackend.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DashboardExpenseByMonthAndYear {

   
    private  int month;
    private  int year;
    private  double totalExpenses;
    
}