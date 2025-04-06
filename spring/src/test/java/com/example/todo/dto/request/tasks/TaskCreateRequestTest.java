package com.example.todo.dto.request.tasks;

import com.example.todo.enums.task.TaskPriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskCreateRequestTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @Test
  void 有効なリクエストであること() {
    TaskCreateRequest request = new TaskCreateRequest("Task Name", null, TaskPriority.LOW);

    Set<ConstraintViolation<TaskCreateRequest>> violations = this.validator.validate(request);

    assertEquals(0, violations.size());
  }

  @Test
  void タスク名が空の場合にエラーになること() {
    TaskCreateRequest request = new TaskCreateRequest("", 1, TaskPriority.LOW);

    Set<ConstraintViolation<TaskCreateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskCreateRequest> violation = violations.iterator().next();
    assertEquals("name", violation.getPropertyPath().toString());
    assertEquals("空要素は許可されていません", violation.getMessage());
  }

  @Test
  void 優先度がnullの場合にエラーになること() {
    TaskCreateRequest request = new TaskCreateRequest("Task Name", null, null);

    Set<ConstraintViolation<TaskCreateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskCreateRequest> violation = violations.iterator().next();
    assertEquals("priority", violation.getPropertyPath().toString());
    assertEquals("null は許可されていません", violation.getMessage());
  }
}
