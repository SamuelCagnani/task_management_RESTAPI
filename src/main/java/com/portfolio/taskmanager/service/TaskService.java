package com.portfolio.taskmanager.service;

import com.portfolio.taskmanager.dto.TaskRequest;
import com.portfolio.taskmanager.dto.TaskResponse;
import com.portfolio.taskmanager.entity.Task;
import com.portfolio.taskmanager.entity.TaskStatus;
import com.portfolio.taskmanager.entity.User;
import com.portfolio.taskmanager.exception.ResourceNotFoundException;
import com.portfolio.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public Page<TaskResponse> getAllTasks(User user, Pageable pageable) {
        log.info("Fetching all tasks for user: {}", user.getUsername());
        return taskRepository.findAllByUser(user, pageable)
                .map(this::mapToResponse);
    }

    public TaskResponse getTaskById(Long id, User user) {
        log.info("Fetching task with id: {} for user: {}", id, user.getUsername());
        return taskRepository.findByIdAndUser(id, user)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request, User user) {
        log.info("Creating new task for user: {}", user.getUsername());
        var task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO)
                .user(user)
                .build();
        return mapToResponse(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request, User user) {
        log.info("Updating task with id: {} for user: {}", id, user.getUsername());
        var task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        
        return mapToResponse(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id, User user) {
        log.info("Deleting task with id: {} for user: {}", id, user.getUsername());
        var task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
