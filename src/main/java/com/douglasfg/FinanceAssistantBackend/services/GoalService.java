package com.douglasfg.FinanceAssistantBackend.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceAlreadyExistsException;
import com.douglasfg.FinanceAssistantBackend.exceptions.ResourceNotFoundException;
import com.douglasfg.FinanceAssistantBackend.handlers.GoalHandler;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;

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
        Optional<Goal> existing = goalRepository.findByMonthAndYear(request.getMonth(), request.getYear());
        if (existing.isPresent()) {
            throw new ResourceAlreadyExistsException(
                "Já existe meta para " + request.getMonth() + "/" + request.getYear()
            );
        }

        Goal goal = Goal.builder()
                .month(request.getMonth())
                .year(request.getYear())
                .ceiling(request.getCeiling())
                .build();

        Goal saved = goalRepository.save(goal);
        return goalHandler.prepareResponse(saved);
    }


   public List<GoalResponse> findAll() {
        return goalRepository.findAll()
                             .stream()
                             .map(goalHandler::prepareResponse) // agora chama o handler
                             .toList();
    }



    public GoalResponse findByMonthAndYear(int month, int year) {
        Goal goal = goalRepository.findByMonthAndYear(month, year)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Meta não encontrada para " + month + "/" + year
                ));
        return goalHandler.prepareResponse(goal);
    }



}

