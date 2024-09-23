package com.example.todo.controller.api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.service.tasks.TaskCreateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskCreateController {

  private final TaskCreateService taskCreateService;

  public TaskCreateController(TaskCreateService taskCreateService) {
    this.taskCreateService = taskCreateService;
  }

  @PostMapping
  public ResponseEntity<TaskBaseResponse> invoke(@Valid @RequestBody TaskCreateRequest request) {
    TaskBaseResponse response = this.taskCreateService.invoke(request);

    return ResponseEntity.ok(response);
  }
}
