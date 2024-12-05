package com.example.todo.service.projects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.repository.ProjectRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ProjectCreateServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private ProjectCreateService projectCreateService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void プロジェクトが作成されること() {
    // モックデータの設定
    Project mockProject = new Project();
    mockProject.setId(1);
    mockProject.setName("Test Project");
    mockProject.setSummary("Test Summary");

    // モックリポジトリの動作を設定
    Mockito.when(this.projectRepository.save(any(Project.class))).thenReturn(mockProject);

    // リクエストオブジェクトを作成
    ProjectCreateRequest request = new ProjectCreateRequest("Test Project", "Test Summary");

    // サービスメソッドを実行
    Project createdProject = this.projectCreateService.invoke(request);

    // 結果を検証
    assertEquals(1, createdProject.getId());
    assertEquals("Test Project", createdProject.getName());
    assertEquals("Test Summary", createdProject.getSummary());

    // リポジトリのsaveメソッドが一回呼ばれたことを確認
    Mockito.verify(this.projectRepository, Mockito.times(1)).save(any(Project.class));
  }
}
