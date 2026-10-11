package com.douglasfg.FinanceAssistantBackend.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private Double cost;

    @NotNull(message = "Data é obrigatória")
    private LocalDate expenseDate;

    // Quem fez a despesa
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // Categorias da despesa (1:1)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Meta associada (N:1, cada despesa pertence a uma meta mensal)
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}
