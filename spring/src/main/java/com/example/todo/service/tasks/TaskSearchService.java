package com.example.todo.service.tasks;

import com.example.todo.dto.request.tasks.TaskSearchRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TaskSearchService {

  private final TaskRepository taskRepository;

  public TaskSearchService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<TaskBaseResponse> invoke(TaskSearchRequest request) {
    // TODO: 以下の処理だと、全てのタスクを取得してしまう。本来は、リクエストパラメータに応じて取得するタスクを絞り込む必要がある。
    List<Task> tasks = this.taskRepository.findAll();

    // Task Entity のリストを、TaskBaseResponse のリストに変換して返す
    // .collect(Collectors.toList()) は、Stream の要素を List に変換するメソッド
    return tasks.stream().map(task -> new TaskBaseResponse(task)).collect(Collectors.toList());
  }
}
