package com.example.todo.service.tasks;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;

@Service
public class TaskCreateService {

  private final TaskRepository taskRepository;

  public TaskCreateService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;

  }

  public TaskBaseResponse invoke(TaskCreateRequest request) {
    Task task = new Task();

    task.setName(request.getName());
    task.setPriority(request.getPriority());

    // TODO: プロジェクトの追加

    return new TaskBaseResponse(this.taskRepository.save(task));
  }
}
