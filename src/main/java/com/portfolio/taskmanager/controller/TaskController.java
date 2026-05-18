package com.portfolio.taskmanager.controller;

import com.portfolio.taskmanager.dto.TaskRequest;
import com.portfolio.taskmanager.dto.TaskResponse;
import com.portfolio.taskmanager.entity.User;
import com.portfolio.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        return ResponseEntity.ok(taskService.getAllTasks(user, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.getTaskById(id, user));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.updateTask(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        taskService.deleteTask(id, user);
        return ResponseEntity.noContent().build();
    }
}
