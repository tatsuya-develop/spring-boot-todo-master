package com.example.todo.controller.api.projects;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.entity.Project;

@RestController
@RequestMapping("/api/projects")
public class ProjectListController {

  public ProjectListController() {
    // Constructor
  }

  @GetMapping
  public ResponseEntity<List<Project>> invoke() {
    // TODO: ダミーデータでの仮実装のため、本修正必須。
    List<Project> projects = List.of(new Project());

    return ResponseEntity.ok(projects);
  }
}
