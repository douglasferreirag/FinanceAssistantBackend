package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;

import com.douglasfg.FinanceAssistantBackend.dtos.ai.GoalAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;

@Mapper(componentModel = "spring")
public interface GoalAnalyzeDataMapper {

    GoalAnalyzeData toData(
            Goal goal
    );

}