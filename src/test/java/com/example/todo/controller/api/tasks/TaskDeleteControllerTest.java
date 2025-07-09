package com.example.todo.controller.api.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.service.tasks.TaskDeleteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskDeleteController.class)
class TaskDeleteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskDeleteService taskDeleteService;

  @Test
  void タスクが削除されること() throws Exception {
    // モックレスポンスの設定
    int taskId = 1;
    // .doNothing ... これは「何もしない」という動作を指定するメソッド
    // 通常はモック化したメソッドに対して特定の動作（戻り値指定 / 例外スロー）を指定するが、
    // ここでは何もせずにメソッドを呼び出しを通過させる。
    Mockito.doNothing().when(taskDeleteService).invoke(taskId);

    // Deleteリクエストをシュミレートして実行
    // 戻り値を返す必要がないため、contentTypeの検証は不要
    mockMvc.perform(delete("/api/tasks/{taskId}", taskId)).andExpect(status().isNoContent());

    // taskDeleteServiceが正しい引数で呼び出されたことを検証
    Mockito.verify(taskDeleteService).invoke(taskId);
  }
}
