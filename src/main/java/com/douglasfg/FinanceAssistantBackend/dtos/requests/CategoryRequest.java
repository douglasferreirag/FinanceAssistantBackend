package com.douglasfg.FinanceAssistantBackend.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Nome da categoria é obrigatório")
    private String name;
}
