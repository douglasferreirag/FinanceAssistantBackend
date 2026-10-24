package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Goal;

@Mapper(componentModel = "spring", uses = {ExpenseMapper.class})
public interface GoalMapper {

    // Request → Entity
    @Mapping(target = "id", ignore = true) // id gerado pelo banco
    @Mapping(target = "month", ignore = false) // month gerado pelo banco
    @Mapping(target = "year", ignore = false) // year gerado pelo banco
    @Mapping(target = "ceiling", ignore = false) // ceiling gerado pelo banco
    @Mapping(target = "expenses", ignore = true) // id gerado pelo banco
    Goal toEntity(GoalRequest request);

       // Entity → Response (campos + lista de despesas)
   // Entity → Response
    @Mapping(source = "id", target = "id")
    @Mapping(source = "month", target = "month")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "ceiling", target = "ceiling")
    @Mapping(source = "expenses", target = "expenses") // usa ExpenseMapper
    GoalResponse toResponse(Goal goal);
}
