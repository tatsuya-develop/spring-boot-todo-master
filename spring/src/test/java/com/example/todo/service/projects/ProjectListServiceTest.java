package com.example.todo.service.projects;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.todo.entity.Project;
import com.example.todo.repository.ProjectRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectListServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private ProjectListService projectListService;

  private List<Project> mockProjects;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    Project project1 = new Project();
    project1.setId(1);
    project1.setName("Project 1");

    Project project2 = new Project();
    project2.setId(2);
    project2.setName("Project 2");

    this.mockProjects = Arrays.asList(project1, project2);

    // リポジトリがfindAll()メソッドでこのモックデータを返すように設定
    Mockito.when(this.projectRepository.findAll()).thenReturn(this.mockProjects);
  }

  @Test
  void Projectの一覧が返されること() {
    // サービスのinvokeメソッドを実行
    List<Project> projects = this.projectListService.invoke();

    // 期待する結果と一致しているか確認
    assertEquals(2, projects.size());
    assertEquals(1, projects.get(0).getId());
    assertEquals(2, projects.get(1).getId());

    // RepositoryのfindAll()が1回だけ呼ばれたことを確認
    Mockito.verify(this.projectRepository, Mockito.times(1)).findAll();
  }
}
