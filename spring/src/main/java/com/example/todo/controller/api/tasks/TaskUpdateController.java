package com.example.todo.controller.api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.tasks.TaskUpdateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.service.tasks.TaskUpdateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskUpdateController {

  private final TaskUpdateService taskUpdateService;

  public TaskUpdateController(TaskUpdateService taskUpdateService) {
    this.taskUpdateService = taskUpdateService;
  }

  @PutMapping
  public ResponseEntity<TaskBaseResponse> invoke(@Valid @RequestBody TaskUpdateRequest request) {
    TaskBaseResponse response = this.taskUpdateService.invoke(request);
    return ResponseEntity.ok(response);
  }
}
