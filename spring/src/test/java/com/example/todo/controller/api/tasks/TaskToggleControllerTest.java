package com.example.todo.controller.api.tasks;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.service.tasks.TaskToggleService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TaskToggleController.class)
class TaskToggleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskToggleService taskToggleService;

  @Test
  void タスクの完了_未完了を切り替えること() throws Exception {
    // モックレスポンスの設定
    Task task = new Task();
    task.setId(1);
    task.setName("Toggled Task");
    LocalDateTime now = LocalDateTime.now();
    task.setCompletedAt(now);
    task.setCreatedAt(now);
    task.setUpdatedAt(now);

    TaskBaseResponse taskBaseResponse = new TaskBaseResponse(task);

    // モックサービスの振る舞いを定義
    Mockito.when(taskToggleService.invoke(1)).thenReturn(taskBaseResponse);

    mockMvc.perform(put("/api/tasks/{taskId}/toggle", 1)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("Toggled Task"))
        // completedAtのフォーマットは TaskBaseResponse のテストで検証する
        .andExpect(jsonPath("$.completedAt").isNotEmpty());
  }
}
