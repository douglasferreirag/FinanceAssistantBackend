package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dtos.ai.GoalAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.mappers.GoalAnalyzeDataMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoalAnalyzeDataHandler {

    private final GoalAnalyzeDataMapper goalAnalyzeDataMapper;

    public GoalAnalyzeData prepareData(
            Goal goal
    ) {

        return goalAnalyzeDataMapper.toData(
                goal
        );

    }

}