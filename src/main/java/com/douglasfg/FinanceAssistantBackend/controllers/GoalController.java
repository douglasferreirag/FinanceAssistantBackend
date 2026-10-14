package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.GoalRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GoalResponse;
import com.douglasfg.FinanceAssistantBackend.services.GoalService;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // Criar meta → 201 Created
    @PostMapping("/save")
    public ResponseEntity<GoalResponse> save(@RequestBody GoalRequest request) {
        GoalResponse saved = goalService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Buscar todas → 200 OK ou 204 No Content
    @GetMapping("/findAll")
    public ResponseEntity<List<GoalResponse>> findAll() {
        List<GoalResponse> goals = goalService.findAll();
        if (goals.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(goals); // 200 OK
    }

    // Buscar meta por mês/ano → 200 OK ou 404 Not Found
    @GetMapping("/findByMonthAndYear")
    public ResponseEntity<GoalResponse> findByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year) {
        GoalResponse goal = goalService.findByMonthAndYear(month, year);
        return ResponseEntity.ok(goal);
    }

    
}
