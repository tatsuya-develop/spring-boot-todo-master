package com.example.todo.service.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.todo.repository.TaskRepository;

class TaskDeleteServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskDeleteService taskDeleteService;

  @BeforeEach
  void setUp() {
    // Mockitoのモックを初期化する
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void タスクが削除されること() {
    // 削除対象のID
    Integer taskId = 1;

    // サービスのinvokeメソッドを実行
    this.taskDeleteService.invoke(taskId);

    // リポジトリのdeleteByIdが正しく呼び出されていることを検証
    Mockito.verify(this.taskRepository, Mockito.times(1)).deleteById(taskId);
  }
}
