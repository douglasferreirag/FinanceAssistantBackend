package com.douglasfg.FinanceAssistantBackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douglasfg.FinanceAssistantBackend.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
     @Query("SELECT p.id FROM Person p WHERE p.name = :name")
     Optional<Long> findIdByName(@Param("name") String name);

     @Query("SELECT p.name FROM Person p")
     List<String> findAllNames();
}
