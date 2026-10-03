package com.douglasfg.FinanceAssistantBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.douglasfg.FinanceAssistantBackend.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
}
