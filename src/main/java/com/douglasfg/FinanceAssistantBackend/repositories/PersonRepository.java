package com.douglasfg.FinanceAssistantBackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglasfg.FinanceAssistantBackend.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
     Optional<Person> findByName(String name);

     @Query("SELECT p.name FROM Person p")
     List<String> findAllNames();
}
