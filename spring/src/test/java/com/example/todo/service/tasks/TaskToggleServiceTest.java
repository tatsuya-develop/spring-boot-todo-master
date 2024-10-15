package com.example.todo.service.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class TaskToggleServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskToggleService taskToggleService;

  private Task mockTask;

  private LocalDateTime now = LocalDateTime.now();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // モックタスクの初期化
    this.mockTask = new Task();
    this.mockTask.setId(1);
    this.mockTask.setCompletedAt(null); // 未完了状態
    this.mockTask.setCreatedAt(this.now);
    this.mockTask.setUpdatedAt(this.now);
  }

  @Test
  void 未完了のタスクを完了に切り替えることができること() {
    // タスクが見つかった場合のリポジトリのモック設定
    Mockito.when(this.taskRepository.findById(1)).thenReturn(Optional.of(this.mockTask));
    Mockito.when(this.taskRepository.save(any(Task.class))).thenReturn(this.mockTask);

    // サービスメソッドを実行
    TaskBaseResponse response = this.taskToggleService.invoke(1);

    // タスクが完了状態に変更されていることを確認
    assertNotNull(this.mockTask.getCompletedAt());
    assertEquals(this.mockTask.getId(), response.getId());

    // リポジトリのsaveメソッドが呼ばれたことを確認
    Mockito.verify(this.taskRepository).save(this.mockTask);
  }

  @Test
  void タスクが見つからない場合に例外がスローされること() {
    // タスクが見つからなかった場合のリポジトリのモック設定
    Mockito.when(this.taskRepository.findById(1)).thenReturn(Optional.empty());

    // 例外がスローされることを確認
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      this.taskToggleService.invoke(1);
    });

    assertEquals("Task not found with ID: 1", exception.getMessage());

    // リポジトリのsaveメソッドが呼ばれていないことを確認
    Mockito.verify(this.taskRepository, Mockito.never()).save(any(Task.class));
  }
}
