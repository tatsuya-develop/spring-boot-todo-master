package com.example.todo.dto.request.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.todo.enums.task.TaskPriority;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TaskUpdateRequestTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @Test
  void 有効なリクエストであること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, TaskPriority.LOW, null, null, null);

    Set<ConstraintViolation<TaskUpdateRequest>> violations = this.validator.validate(request);

    assertTrue(violations.isEmpty());
  }

  @Test
  void IDがnullの場合にエラーになること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(null, "Task name", null, TaskPriority.LOW, null, null, null);

    Set<ConstraintViolation<TaskUpdateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskUpdateRequest> violation = violations.iterator().next();
    assertEquals("id", violation.getPropertyPath().toString());
    assertEquals("null は許可されていません", violation.getMessage());
  }

  @Test
  void IDが0の場合にエラーになること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(0, "Task name", null, TaskPriority.LOW, null, null, null);

    Set<ConstraintViolation<TaskUpdateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskUpdateRequest> violation = violations.iterator().next();
    assertEquals("id", violation.getPropertyPath().toString());
    assertEquals("0 より大きな値にしてください", violation.getMessage());
  }

  @Test
  void タスク名が空の場合にエラーになること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "", null, TaskPriority.LOW, null, null, null);

    Set<ConstraintViolation<TaskUpdateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskUpdateRequest> violation = violations.iterator().next();
    assertEquals("name", violation.getPropertyPath().toString());
    assertEquals("空要素は許可されていません", violation.getMessage());
  }

  @Test
  void 優先度がnullの場合にエラーになること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, null, null, null, null);

    Set<ConstraintViolation<TaskUpdateRequest>> violations = this.validator.validate(request);

    assertEquals(1, violations.size());

    ConstraintViolation<TaskUpdateRequest> violation = violations.iterator().next();
    assertEquals("priority", violation.getPropertyPath().toString());
    assertEquals("null は許可されていません", violation.getMessage());
  }

  @Test
  void deadlineAtがnullでない場合にLocalDateTimeに変換されること() {
    ZonedDateTime deadlineAt = ZonedDateTime.now();
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, TaskPriority.LOW, null, deadlineAt, null);

    LocalDateTime convertedDeadlineAt = request.getDeadlineAt();

    assertEquals(deadlineAt.toLocalDateTime(), convertedDeadlineAt);
  }

  @Test
  void deadlineAtがnullの場合にnullが返されること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, TaskPriority.LOW, null, null, null);

    LocalDateTime convertedDeadlineAt = request.getDeadlineAt();
    assertNull(convertedDeadlineAt);
  }

  @Test
  void completedAtがnullでない場合にLocalDateTimeに変換されること() {
    ZonedDateTime completedAt = ZonedDateTime.now();
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, TaskPriority.LOW, null, null, completedAt);

    LocalDateTime convertedCompletedAt = request.getCompletedAt();

    assertEquals(completedAt.toLocalDateTime(), convertedCompletedAt);
  }

  @Test
  void completedAtがnullの場合にnullが返されること() {
    TaskUpdateRequest request =
        new TaskUpdateRequest(1, "Task name", null, TaskPriority.LOW, null, null, null);

    LocalDateTime convertedCompletedAt = request.getCompletedAt();
    assertNull(convertedCompletedAt);
  }
}
