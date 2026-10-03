package com.douglasfg.FinanceAssistantBackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.services.GoalService;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // Criar meta → 201 Created
    @PostMapping("/save")
    public ResponseEntity<Goal> save(@RequestBody Goal goal) {
        Goal saved = goalService.save(goal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Buscar todas → 200 OK ou 204 No Content
    @GetMapping("/findAll")
    public ResponseEntity<List<Goal>> findAll() {
        List<Goal> goals = goalService.findAll();
        if (goals.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(goals); // 200 OK
    }

    @GetMapping("/findByMonthAndYear")
    public ResponseEntity<Double> findByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year) {
        Goal goal = goalService.findByMonthAndYear(month, year);
        return ResponseEntity.ok(goal.getCeiling());
    }

}
