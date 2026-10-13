// GoalRequest.java
package com.douglasfg.FinanceAssistantBackend.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data

public class GoalRequest {
    @NotNull(message = "O mês é obrigatório")
    @Positive(message = "O mês deve ser positivo")
    private int month;

    @NotNull(message = "O ano é obrigatório")
    @Positive(message = "O ano deve ser positivo")
    private int year;

    @NotNull(message = "O valor limite é obrigatório")
    @Positive(message = "O valor limite deve ser positivo")
    private Double ceiling;
}
