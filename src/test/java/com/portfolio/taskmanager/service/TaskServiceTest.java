package com.portfolio.taskmanager.service;

import com.portfolio.taskmanager.dto.TaskRequest;
import com.portfolio.taskmanager.dto.TaskResponse;
import com.portfolio.taskmanager.entity.Task;
import com.portfolio.taskmanager.entity.TaskStatus;
import com.portfolio.taskmanager.entity.User;
import com.portfolio.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private User user;
    private TaskRequest taskRequest;
    private Task task;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).username("testuser").build();
        taskRequest = TaskRequest.builder().title("Test Task").description("Description").build();
        task = Task.builder().id(1L).title("Test Task").description("Description").status(TaskStatus.TODO).user(user).build();
    }

    @Test
    void createTask_ShouldReturnTaskResponse() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskResponse response = taskService.createTask(taskRequest, user);

        assertNotNull(response);
        assertEquals(task.getTitle(), response.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}
