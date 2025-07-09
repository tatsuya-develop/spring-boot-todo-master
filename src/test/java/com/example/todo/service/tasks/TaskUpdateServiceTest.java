package com.example.todo.service.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.todo.dto.request.tasks.TaskUpdateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

class TaskUpdateServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private TaskUpdateService taskUpdateService;

  private Task originalTask;
  private Task updatedTask;
  private Project project;
  private TaskUpdateRequest request;
  private LocalDateTime now = LocalDateTime.now();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // モックタスクの作成
    this.originalTask = new Task();
    this.originalTask.setId(1);
    this.originalTask.setName("Old Task");

    // 関連テーブルのモック設定
    this.project = new Project();
    this.project.setId(1);

    // 更新後のモックタスク
    this.updatedTask = new Task();
    this.updatedTask.setId(1);
    this.updatedTask.setProject(this.project);
    this.updatedTask.setName("Updated Task");
    this.updatedTask.setPriority(TaskPriority.HIGH);
    this.updatedTask.setMemo("Updated memo");
    this.updatedTask.setCreatedAt(this.now);
    this.updatedTask.setUpdatedAt(this.now);

    this.request =
        new TaskUpdateRequest(1, "Updated Task", 1, TaskPriority.HIGH, "Updated memo", null, null);
  }

  @Test
  void タスクが更新されること() {
    when(this.taskRepository.findById(1)).thenReturn(Optional.of(this.originalTask));
    when(this.projectRepository.findById(1)).thenReturn(Optional.of(this.project));
    when(this.taskRepository.save(any(Task.class))).thenReturn(this.updatedTask);

    // サービスメソッド実行
    TaskBaseResponse response = this.taskUpdateService.invoke(this.request);

    // 検証: タスクが正しく更新されているか
    assertEquals("Updated Task", response.getName());
    assertEquals("Updated memo", response.getMemo());
    assertEquals(TaskPriority.HIGH.getValue(), response.getPriority().getValue());

    // プロジェクトが正しく設定されていること
    assertEquals(this.project, response.getProject());

    // 保存処理が呼ばれたことを確認
    verify(this.taskRepository).save(this.originalTask);
  }

  @Test
  void プロジェクトが存在しない場合に例外がスローされること() {
    // タスクが見つかったがプロジェクトが見つからない場合の設定
    when(this.taskRepository.findById(1)).thenReturn(Optional.of(this.originalTask));
    when(this.projectRepository.findById(1)).thenReturn(Optional.empty());

    // サービスメソッド実行
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      this.taskUpdateService.invoke(this.request);
    });

    // エラーメッセージが正しいことを確認
    assertEquals("Project not found with ID: 1", exception.getMessage());
  }

  @Test
  void タスクが存在しない場合に例外がスローされること() {
    // タスクが見つからなかった場合の設定
    when(this.taskRepository.findById(1)).thenReturn(Optional.empty());

    // サービスメソッド実行
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      this.taskUpdateService.invoke(this.request);
    });

    // エラーメッセージが正しいことを確認
    assertEquals("Task not found with ID: 1", exception.getMessage());
  }
}
