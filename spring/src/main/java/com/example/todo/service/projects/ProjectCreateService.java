package com.example.todo.service.projects;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;

/**
 * @Service Spring MVCのサービスとして振る舞うクラスに付与するアノテーション。このアノテーションが付与されたクラスは、ビジネスロジックを持つサービスとして動作する。
 */
@Service
public class ProjectCreateService {

  // コンストラクタ
  public ProjectCreateService() {}

  public Project invoke(ProjectCreateRequest request) {
    Project project = new Project();

    project.setName(request.getName());
    project.setSummary(request.getSummary());

    // TODO: projectをDBに保存するロジックを実装する。
    return project;
  }
}
