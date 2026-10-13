package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dto.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dto.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    // Request → Entity
    @Mapping(target = "month", ignore = true) // month gerado pelo banco
    @Mapping(target = "year", ignore = true) // year gerado pelo banco
    @Mapping(target = "ceiling", ignore = true) // ceiling gerado pelo banco
    Goal toEntity(GoalRequest request);

   
    GoalResponse toResponse(Goal goal);
}
