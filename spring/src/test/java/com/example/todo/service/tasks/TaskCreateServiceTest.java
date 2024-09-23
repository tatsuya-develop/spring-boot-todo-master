package com.example.todo.service.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class TaskCreateServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private ProjectRepository projectRepository;

  /**
   * @InjectMocks テスト対象のクラス（今回だとTaskCreateService）に、@Mock で作成したモックを注入する。
   *              具体的には、@Mockアノテーションでモック化されたTaskRepositoryとProjectRepositoryが、TaskCreateServiceのコンストラクタに渡される。
   *              これにより、依存関係がモックオブジェクトで自動的に満たされるため、テスト対象クラスを手動でインスタンス化する必要がなくなる。
   */
  @InjectMocks
  private TaskCreateService taskCreateService;

  private LocalDateTime now = LocalDateTime.now();

  @BeforeEach
  void setUp() {
    // Mockito のアノテーション（@Mock, @Spy, @InjectMocks）でマークされたフィールドにモックを注入するためのメソッド
    // 内部的には、Mockito が各フィールドに対してモックオブジェクトを生成し、それをセットする
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void プロジェクト未指定の場合にタスクが正常に作成されること() {
    // モックのリクエストデータ
    TaskCreateRequest request = new TaskCreateRequest("Test Task", null, TaskPriority.LOW);

    // タスクを保存する際にリポジトリが返すモックTaskデータ
    Task savedTask = new Task();
    savedTask.setId(1);
    savedTask.setName(request.getName());
    savedTask.setPriority(request.getPriority());
    savedTask.setCreatedAt(this.now);
    savedTask.setUpdatedAt(this.now);

    Mockito.when(this.taskRepository.save(any(Task.class))).thenReturn(savedTask);

    // サービスメソッドを実行
    TaskBaseResponse response = taskCreateService.invoke(request);

    // 結果を検証
    assertEquals(savedTask.getId(), response.getId());
    assertEquals(savedTask.getName(), response.getName());
    assertEquals(savedTask.getPriority().getValue(), response.getPriority().getValue());
    Mockito.verify(this.taskRepository, Mockito.times(1)).save(any(Task.class));
  }

  @Test
  void プロジェクトを指定している場合にタスクが正常に作成されること() {
    // モックのリクエストデータ
    TaskCreateRequest request = new TaskCreateRequest("Test Task", 1, TaskPriority.LOW);

    // モックのプロジェクト
    Project project = new Project();
    project.setId(1);
    project.setName("Test Project");

    // タスクを保存する際にリポジトリが返すモックTaskデータ
    Task savedTask = new Task();
    savedTask.setId(1);
    savedTask.setProject(project);
    savedTask.setName(request.getName());
    savedTask.setPriority(request.getPriority());
    savedTask.setCreatedAt(this.now);
    savedTask.setUpdatedAt(this.now);

    // .findById() は Optional を返すため、Optional.of() でラップして返す必要がある
    Mockito.when(this.projectRepository.findById(1)).thenReturn(Optional.of(project));
    Mockito.when(this.taskRepository.save(any(Task.class))).thenReturn(savedTask);

    // サービスメソッドを実行
    TaskBaseResponse response = taskCreateService.invoke(request);

    // 結果を検証
    assertEquals(savedTask.getId(), response.getId());
    assertEquals(savedTask.getProject().getId(), response.getProject().getId());
    assertEquals(savedTask.getName(), response.getName());
    assertEquals(savedTask.getPriority().getValue(), response.getPriority().getValue());
    Mockito.verify(this.taskRepository, Mockito.times(1)).save(any(Task.class));
    Mockito.verify(this.projectRepository, Mockito.times(1)).findById(1);
  }

  @Test
  void プロジェクトが存在しない場合に例外がスローされること() {
    // モックのリクエストデータ
    TaskCreateRequest request = new TaskCreateRequest("Test Task", 1, TaskPriority.LOW);

    // プロジェクトが存在しないことをモック
    Mockito.when(this.projectRepository.findById(1)).thenReturn(Optional.empty());

    // サービスを実行し、例外が発生することを確認
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      this.taskCreateService.invoke(request);
    });

    // 結果を検証
    assertEquals("Project not found with ID: 1", exception.getMessage());
    Mockito.verify(this.projectRepository, Mockito.times(1)).findById(1);
    // neber() は、「一度も呼ばれなかったこと」を検証するためのメソッド
    Mockito.verify(this.taskRepository, Mockito.never()).save(any(Task.class));
  }
}
