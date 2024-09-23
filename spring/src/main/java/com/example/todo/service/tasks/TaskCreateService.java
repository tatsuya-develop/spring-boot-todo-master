package com.example.todo.service.tasks;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;

@Service
public class TaskCreateService {

  public TaskCreateService() {

  }

  public TaskBaseResponse invoke(TaskCreateRequest request) {
    Task task = new Task();

    return new TaskBaseResponse(task);
  }
}
