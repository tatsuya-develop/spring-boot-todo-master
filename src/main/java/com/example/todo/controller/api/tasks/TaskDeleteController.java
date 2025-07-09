package com.example.todo.controller.api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.service.tasks.TaskDeleteService;

@RestController
@RequestMapping("/api/tasks")
public class TaskDeleteController {

  private final TaskDeleteService taskDeleteService;

  public TaskDeleteController(TaskDeleteService taskDeleteService) {
    this.taskDeleteService = taskDeleteService;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> invoke(@PathVariable Integer id) {
    this.taskDeleteService.invoke(id);
    return ResponseEntity.noContent().build();
  }
}
