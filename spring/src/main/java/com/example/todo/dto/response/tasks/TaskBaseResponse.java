package com.example.todo.dto.response.tasks;

import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.util.TimeUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskBaseResponse {
  private Integer id;

  private Project project;

  private Integer parentId;

  private String name;

  private TaskPriorityResponse priority;

  private String memo;

  private String deadlineAt;

  private String completedAt;

  private String createdAt;

  private String updatedAt;

  public TaskBaseResponse(Task task) {
    this.id = task.getId();
    this.project = task.getProject();
    this.name = task.getName();
    this.priority = new TaskPriorityResponse(task.getPriority());
    this.memo = task.getMemo();
    this.deadlineAt = this.formatDateTime(task.getDeadlineAt());
    this.completedAt = this.formatDateTime(task.getCompletedAt());
    this.createdAt = this.formatDateTime(task.getCreatedAt());
    this.updatedAt = this.formatDateTime(task.getUpdatedAt());
  }

  private String formatDateTime(LocalDateTime dateTime) {
    return dateTime != null ? TimeUtil.Format.toYmdhm(dateTime) : null;
  }
}
