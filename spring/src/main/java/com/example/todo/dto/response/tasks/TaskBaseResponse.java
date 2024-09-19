package com.example.todo.dto.response.tasks;

import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskBaseResponse {
  private Integer id;

  private Project project;

  private Integer parentId;

  private String name;

  // レスポンスされる際は、TaskPriority の値を 0, 1, 2 で返す。
  // TODO: 理想的なレスポンスは、TaskPriority の key, value, label それぞれを返すこと。
  private TaskPriority priority;

  private String memo;

  // TODO: LocalDateTime で返す場合、ISO 8601 形式（yyyy-MM-dd'T'HH:mm:ss）で返されてしまう。
  // これを yyyy-MM-dd HH:mm で返すように変更したい。
  private LocalDateTime deadlineAt;

  private LocalDateTime completedAt;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public TaskBaseResponse(Task task) {
    this.id = task.getId();
    this.project = task.getProject();
    this.name = task.getName();
    this.priority = task.getPriority();
    this.memo = task.getMemo();
    this.deadlineAt = task.getDeadlineAt() != null ? task.getDeadlineAt() : null;
    this.completedAt = task.getCompletedAt() != null ? task.getCompletedAt() : null;
    this.createdAt = task.getCreatedAt();
    this.updatedAt = task.getUpdatedAt();
  }
}
