package com.example.todo.controller.api.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.enums.task.TaskPriority;
import com.example.todo.service.tasks.TaskCreateService;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TaskCreateController.class)
class TaskCreateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskCreateService taskCreateService;

  @Test
  void 作成されたTaskが返されること() throws Exception {
    // モックレスポンスの設定
    Task task = new Task();
    task.setId(1);
    task.setName("New Task");
    task.setPriority(TaskPriority.MEDIUM);
    LocalDateTime now = LocalDateTime.now();
    task.setCreatedAt(now);
    task.setUpdatedAt(now);

    TaskBaseResponse response = new TaskBaseResponse(task);

    Mockito.when(this.taskCreateService.invoke(Mockito.any(TaskCreateRequest.class)))
        .thenReturn(response);

    String requestBody = """
        {
          "name": "New Task",
          "projectId": null,
          "priority": 1
        }
        """;

    this.mockMvc
        .perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("New Task"))
        .andExpect(jsonPath("$.project").isEmpty())
        .andExpect(jsonPath("$.priority.value").value(TaskPriority.MEDIUM.getValue()));
  }
}
