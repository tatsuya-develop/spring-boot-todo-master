package com.example.todo.controller.api.tasks;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.dto.request.tasks.TaskUpdateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import com.example.todo.service.tasks.TaskUpdateService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TaskUpdateController.class)
class TaskUpdateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskUpdateService taskUpdateService;

  @Test
  void タスクが更新されること() throws Exception {
    // モックレスポンスの設定
    Task task = new Task();
    task.setId(1);
    task.setName("Updated Task");
    task.setPriority(TaskPriority.MEDIUM);
    LocalDateTime now = LocalDateTime.now();
    task.setCreatedAt(now);
    task.setUpdatedAt(now);

    String requestBody = """
        {
          "id": 1,
          "name": "Updated Task",
          "projectId": null,
          "tagIds": [],
          "priority": 1,
          "memo": "",
          "deadlineAt": null,
          "completedAt": null
        }
        """;

    TaskBaseResponse taskBaseResponse = new TaskBaseResponse(task);

    Mockito.when(taskUpdateService.invoke(Mockito.any(TaskUpdateRequest.class)))
        .thenReturn(taskBaseResponse);

    // リクエスト実行
    mockMvc.perform(put("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("Updated Task"))
        .andExpect(jsonPath("$.priority.value").value(TaskPriority.MEDIUM.getValue()));
  }
}
