package com.example.todo.controller.api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.tasks.TaskSearchRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.service.tasks.TaskSearchService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskSearchController {

  private final TaskSearchService taskSearchService;

  public TaskSearchController(TaskSearchService taskSearchService) {
    this.taskSearchService = taskSearchService;
  }

  /**
   * @ModelAttribute メソッドの引数に付与するアノテーション。リクエストパラメータを受け取るために使用する。
   *                 リクエストパラメータとは、HTTPリクエストのクエリパラメータやフォームパラメータのこと。
   *                 例えば、`/api/tasks?status=xxx`のようなリクエストの status パラメータを受け取る場合に使用する。
   */
  @GetMapping
  public ResponseEntity<List<TaskBaseResponse>> invoke(
      @Valid @ModelAttribute TaskSearchRequest request) {
    List<TaskBaseResponse> tasks = this.taskSearchService.invoke(request);

    return ResponseEntity.ok(tasks);
  }
}
