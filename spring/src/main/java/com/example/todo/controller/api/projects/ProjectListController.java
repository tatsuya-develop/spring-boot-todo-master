package com.example.todo.controller.api.projects;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectListService;

@RestController
@RequestMapping("/api/projects")
public class ProjectListController {

  private final ProjectListService projectListService;

  public ProjectListController(ProjectListService projectListService) {
    this.projectListService = projectListService;
  }

  @GetMapping
  public ResponseEntity<List<Project>> invoke() {
    List<Project> projects = this.projectListService.invoke();

    return ResponseEntity.ok(projects);
  }
}
