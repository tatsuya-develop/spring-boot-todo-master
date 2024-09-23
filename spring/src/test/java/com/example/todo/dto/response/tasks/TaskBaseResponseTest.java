package com.example.todo.dto.response.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskBaseResponseTest {

  private Task mockTask;
  private Project mockProject;

  @BeforeEach
  void setUp() {
    LocalDateTime time = LocalDateTime.of(2024, 9, 1, 10, 0, 0);

    this.mockTask = new Task();
    this.mockProject = new Project();

    this.mockTask.setId(1);
    this.mockTask.setProject(this.mockProject);
    this.mockTask.setName("Sample Task");
    this.mockTask.setPriority(TaskPriority.LOW);
    this.mockTask.setMemo("Sample Memo");
    this.mockTask.setDeadlineAt(time);
    this.mockTask.setCompletedAt(time);
    this.mockTask.setCreatedAt(time);
    this.mockTask.setUpdatedAt(time);
  }

  @Test
  void 全てのフィールドが正しく設定されていること() {
    TaskBaseResponse response = new TaskBaseResponse(this.mockTask);

    assertEquals(1, response.getId());
    assertEquals(this.mockProject, response.getProject());
    assertEquals("Sample Task", response.getName());
    assertEquals(TaskPriorityResponse.class, response.getPriority().getClass());
    assertEquals(new TaskPriorityResponse(TaskPriority.LOW).getValue(),
        response.getPriority().getValue());
    assertEquals("Sample Memo", response.getMemo());
    assertEquals("2024-09-01 10:00", response.getDeadlineAt());
    assertEquals("2024-09-01 10:00", response.getCompletedAt());
    assertEquals("2024-09-01 10:00", response.getCreatedAt());
    assertEquals("2024-09-01 10:00", response.getUpdatedAt());
  }

  @Test
  void 締切日時や完了日時がnullの場合でも正しく設定されていること() {
    this.mockTask.setDeadlineAt(null);
    this.mockTask.setCompletedAt(null);

    TaskBaseResponse response = new TaskBaseResponse(this.mockTask);

    assertNull(response.getDeadlineAt());
    assertNull(response.getCompletedAt());
  }
}
