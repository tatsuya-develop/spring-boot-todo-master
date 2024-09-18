package com.example.todo.controller.api.projects;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectListService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProjectListController.class)
class ProjectListControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProjectListService projectListService;

  private List<Project> mockProjects;

  /**
   * @BeforeEach 各テストメソッドの実行前に実行されるメソッドを示す。このアノテーションが付与されたメソッド（setUp）は、各テストメソッドの実行前に実行される。
   *             この準備には、モックオブジェクトの設定や共通のテストデータの初期化が含まれる。
   */
  @BeforeEach
  void setUp() {
    // モックレスポンスの設定
    Project project1 = new Project();
    project1.setId(1);
    project1.setName("Project 1");

    Project project2 = new Project();
    project2.setId(2);
    project2.setName("Project 2");

    this.mockProjects = Arrays.asList(project1, project2);

    Mockito.when(this.projectListService.invoke()).thenReturn(this.mockProjects);
  }

  @Test
  void Projectの一覧が返されること() throws Exception {
    mockMvc.perform(get("/api/projects").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].name").value("Project 1"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Project 2"));
  }
}
