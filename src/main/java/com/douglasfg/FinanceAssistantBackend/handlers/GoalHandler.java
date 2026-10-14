package com.douglasfg.FinanceAssistantBackend.handlers;

import org.springframework.stereotype.Component;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.mappers.GoalMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Data
public class GoalHandler {

    private final GoalMapper goalMapper;

    public Goal prepareGoal(GoalRequest request) {
        return goalMapper.toEntity(request);
    }

    public GoalResponse prepareResponse(Goal goal) {
        return goalMapper.toResponse(goal);
    }
}
