package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    // Request → Entity
    @Mapping(target = "id", ignore = true) // id gerado pelo banco
    @Mapping(target = "month", ignore = true) // month gerado pelo banco
    @Mapping(target = "year", ignore = true) // year gerado pelo banco
    @Mapping(target = "ceiling", ignore = true) // ceiling gerado pelo banco
     @Mapping(target = "expenses", ignore = true) // id gerado pelo banco
    
    Goal toEntity(GoalRequest request);

   
    GoalResponse toResponse(Goal goal);
}
