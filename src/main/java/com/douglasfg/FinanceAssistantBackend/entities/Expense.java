package com.douglasfg.FinanceAssistantBackend.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Categorias da despesa (N:N)
    @ManyToMany
    @JoinTable(
        name = "expense_category",
        joinColumns = @JoinColumn(name = "expense_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // Meta associada (N:1, cada despesa pertence a uma meta mensal)
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}
