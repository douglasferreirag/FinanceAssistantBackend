package com.douglasfg.FinanceAssistantBackend.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {
    @Id 
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private Double cost;

    @NotNull(message = "Data é obrigatória")
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Categoria é obrigatória")
    @JsonBackReference
    private Category category;


}
