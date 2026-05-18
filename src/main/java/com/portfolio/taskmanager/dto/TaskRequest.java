package com.portfolio.taskmanager.dto;

import com.portfolio.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank
    private String title;
    private String description;
    private TaskStatus status;
}
