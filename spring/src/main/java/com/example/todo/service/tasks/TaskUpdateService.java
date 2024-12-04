package com.example.todo.service.tasks;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskUpdateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskUpdateService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;

  public TaskUpdateService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
  }

  public TaskBaseResponse invoke(TaskUpdateRequest request) {
    Task task = this.taskRepository.findById(request.getId()).orElseThrow(
        () -> new EntityNotFoundException("Task not found with ID: " + request.getId()));

    if (request.getProjectId() != null) {
      task.setProject(this.projectRepository.findById(request.getProjectId().intValue())
          .orElseThrow(() -> new EntityNotFoundException(
              "Project not found with ID: " + request.getProjectId())));
    } else {
      task.setProject(null);
    }

    // DTO の同名プロパティをEntity にコピー（id は対象外とする）
    // 第一引数をコピー元、第二引数をコピー先 とし、同じ名前のプロパティの値をコピーする処理。
    // プロパティ名と型が一致している場合のみ、コピーされる。
    // コピーしたくないプロパティが存在する場合は、第三引数で指定する。
    // 今回は "id" を設定していて、データ更新では自身のIDを変更することは無いため、明示的にコピー対象外としている。
    BeanUtils.copyProperties(request, task, "id");

    return new TaskBaseResponse(this.taskRepository.save(task));
  }
}
