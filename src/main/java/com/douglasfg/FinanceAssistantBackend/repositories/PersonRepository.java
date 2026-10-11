package com.douglasfg.FinanceAssistantBackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.douglasfg.FinanceAssistantBackend.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
     Optional<Person> findByName(String name);
}
