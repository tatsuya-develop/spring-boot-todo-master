package com.example.todo.service.tasks;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskCreateService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;

  public TaskCreateService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
  }

  public TaskBaseResponse invoke(TaskCreateRequest request) {
    Task task = new Task();

    task.setName(request.getName());
    task.setPriority(request.getPriority());

    // ProjectIDが指定されている場合は、プロジェクトを設定する。
    if (request.getProjectId() != null) {

      // request.getProjectId().intValue(): requestオブジェクトからプロジェクトIDを取得し、それを整数値に変換する。
      // this.projectRepository.findById(...): projectRepositoryを使用して、指定されたプロジェクトIDでプロジェクトを検索する。
      // ____________________________________（findByIdメソッドは、指定されたIDに一致するプロジェクトを検索し、Optional<Project>を返します。）
      // .orElseThrow(() -> new EntityNotFoundException(...)):
      // プロジェクトが見つからない場合は、EntityNotFoundExceptionをスローします。
      Project project = this.projectRepository.findById(request.getProjectId().intValue())
          .orElseThrow(() -> new EntityNotFoundException(
              "Project not found with ID: " + request.getProjectId()));

      task.setProject(project);
    }

    return new TaskBaseResponse(this.taskRepository.save(task));
  }
}
