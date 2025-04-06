package com.example.todo.dto.response.tasks;

import org.junit.jupiter.api.Test;
import com.example.todo.enums.task.TaskPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskPriorityResponseTest {

  @Test
  void 全てのフィールドが正しく設定されていること() {
    TaskPriority priority = TaskPriority.MEDIUM;

    TaskPriorityResponse response = new TaskPriorityResponse(priority);

    assertEquals(priority.name(), response.getName());
    assertEquals(priority.ordinal(), response.getValue());
    assertEquals(priority.getLabel(), response.getLabel());
  }
}
