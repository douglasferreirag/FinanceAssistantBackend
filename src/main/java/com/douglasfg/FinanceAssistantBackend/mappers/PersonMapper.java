package com.douglasfg.FinanceAssistantBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.PersonRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.PersonResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)       // id gerado pelo banco
    @Mapping(target = "expenses", ignore = true) // não mapeamos lista de despesas
   
    Person toEntity(PersonRequest request);

    
    PersonResponse toResponse(Person person);
}
