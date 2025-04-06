package com.example.todo.dto.request.tasks;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import com.example.todo.enums.task.TaskPriority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaskUpdateRequest {

  @NotNull
  @Positive
  private final Integer id;

  @NotEmpty
  private final String name;

  private final Integer projectId;

  @NotNull
  private final TaskPriority priority;

  private final String memo;

  // LocalDateTimeで受け取ってしまうと、UTCで受け取ってしまうため、ZonedDateTimeで受け取る（原因不明）
  private final ZonedDateTime deadlineAt;

  // LocalDateTimeで受け取ってしまうと、UTCで受け取ってしまうため、ZonedDateTimeで受け取る（原因不明）
  private final ZonedDateTime completedAt;

  // getter では LocalDateTime に変換して返す
  public LocalDateTime getDeadlineAt() {
    return this.deadlineAt != null ? this.deadlineAt.toLocalDateTime() : null;
  }

  // getter では LocalDateTime に変換して返す
  public LocalDateTime getCompletedAt() {
    return this.completedAt != null ? this.completedAt.toLocalDateTime() : null;
  }
}
