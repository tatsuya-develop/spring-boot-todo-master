package com.example.todo.service.projects;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.repository.ProjectRepository;

/**
 * @Service Spring MVCのサービスとして振る舞うクラスに付与するアノテーション。このアノテーションが付与されたクラスは、ビジネスロジックを持つサービスとして動作する。
 */
@Service
public class ProjectCreateService {

  private final ProjectRepository projectRepository;

  // コンストラクタ
  public ProjectCreateService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Project invoke(ProjectCreateRequest request) {
    Project project = new Project();

    project.setName(request.getName());
    project.setSummary(request.getSummary());

    // .save()メソッドを使用して、ProjectRepositoryのsaveメソッドを呼び出し、引数にprojectを渡すことで、データベースにデータを保存する。
    return this.projectRepository.save(project);
  }
}
