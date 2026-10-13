package com.douglasfg.FinanceAssistantBackend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mês e ano da meta
    @NotNull(message = "O mês é obrigatório")
    @Positive(message = "O mês deve ser positivo")
    private int month;

    @NotNull(message = "O ano é obrigatório")
    @Positive(message = "O ano deve ser positivo")
    private int year;

    // Valor limite (teto)
    @NotNull(message = "O valor limite é obrigatório")
    @Positive(message = "O valor limite deve ser positivo")
    private Double ceiling;

    // Relacionamento: uma meta pode ter várias despesas associadas
    @Builder.Default
    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();
}
