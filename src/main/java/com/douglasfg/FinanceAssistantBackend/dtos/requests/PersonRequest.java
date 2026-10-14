package com.douglasfg.FinanceAssistantBackend.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonRequest {
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;
}
