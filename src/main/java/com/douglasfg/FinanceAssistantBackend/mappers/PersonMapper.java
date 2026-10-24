package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.PersonRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.PersonResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Person;

@Mapper(componentModel = "spring", uses = {ExpenseMapper.class})
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)       // id gerado pelo banco
    @Mapping(target = "expenses", ignore = true) // não mapeamos lista de despesas
   
    Person toEntity(PersonRequest request);

     // Entity → Response
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "expenses", target = "expenses") // usa ExpenseMapper
    PersonResponse toResponse(Person person);
}
