package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;
import com.douglasfg.FinanceAssistantBackend.dto.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dto.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.handlers.GoalHandler;

import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceAlreadyExistsException;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
@Data
public class GoalService {

    
    private final GoalRepository goalRepository;
    private final GoalHandler goalHandler;



    @Transactional
    public GoalResponse save(GoalRequest request) {
        // Verifica se já existe meta para o mesmo mês/ano
        goalRepository.findByMonthAndYear(request.getMonth(), request.getYear())
                .ifPresent(g -> {
                    throw new ResourceAlreadyExistsException(
                        "Já existe meta para " + request.getMonth() + "/" + request.getYear()
                    );
                });

        // Monta a entidade Goal a partir do request
        Goal goal = Goal.builder()
                .month(request.getMonth())
                .year(request.getYear())
                .ceiling(request.getCeiling())
                .build();

        // Salva no banco
        Goal saved = goalRepository.save(goal);

        // Converte para resposta
        return goalHandler.prepareResponse(saved);
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    public Goal findByMonthAndYear(int month, int year) {
        return goalRepository.findByMonthAndYear(month, year);
    }


}

