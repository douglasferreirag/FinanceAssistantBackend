package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
@Data
public class GoalService {

    
    private final GoalRepository goalRepository;



    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    public Goal findByMonthAndYear(int month, int year) {
        return goalRepository.findByMonthAndYear(month, year);
    }


}

