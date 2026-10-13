package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;

import lombok.RequiredArgsConstructor;

import com.douglasfg.FinanceAssistantBackend.mappers.GoalMapper;
import com.douglasfg.FinanceAssistantBackend.dto.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dto.responses.GoalResponse;

@Component
@RequiredArgsConstructor
public class GoalHandler {

    private final GoalMapper goalMapper;

    public Goal prepareGoal(GoalRequest request) {
        return goalMapper.toEntity(request);
    }

    public GoalResponse prepareResponse(Goal goal) {
        return goalMapper.toResponse(goal);
    }
}
