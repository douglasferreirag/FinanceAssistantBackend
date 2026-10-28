package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.dtos.requests.CategoryRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.CategoryResponse;
import com.douglasfg.FinanceAssistantBackend.services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Criar categoria → 201 Created
    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        CategoryResponse saved = categoryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Buscar todas → 200 OK (ou 204 No Content se vazio)
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(categories); // 200 OK
    }


    @GetMapping("/findIdByName/{name}")
    public ResponseEntity<CategoryResponse> findIdByName(@PathVariable String name) {
        CategoryResponse category = categoryService.findIdByName(name);
        return ResponseEntity.ok(category);
    }


    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(categoryService.getAllNames());
    }

   
}
