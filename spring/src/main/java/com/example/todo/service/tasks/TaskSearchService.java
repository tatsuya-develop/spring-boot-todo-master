package com.example.todo.service.tasks;

import com.example.todo.dto.request.tasks.TaskSearchRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.specification.TaskSpecification;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TaskSearchService {

  private final TaskRepository taskRepository;

  public TaskSearchService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<TaskBaseResponse> invoke(TaskSearchRequest request) {
    Specification<Task> spec = this.generateSpecification(request);
    Sort sort = this.generateSort();

    // Task Entity のリストを取得
    // 引数に検索条件とソート条件を指定することで、条件に合致する Task Entity のリストを取得できる
    List<Task> tasks = this.taskRepository.findAll(spec, sort);

    // Task Entity のリストを、TaskBaseResponse のリストに変換して返す
    // .collect(Collectors.toList()) は、Stream の要素を List に変換するメソッド
    return tasks.stream().map(task -> new TaskBaseResponse(task)).collect(Collectors.toList());
  }

  /**
   * Task Entity に対する検索条件を生成する。
   *
   * @param request
   * @return
   */
  private Specification<Task> generateSpecification(TaskSearchRequest request) {
    TaskSpecification spec = new TaskSpecification();

    // プロジェクトIDが null の場合は、projectIdIsNull() を、それ以外の場合は projectIdEqual() を使用する
    return request.getProjectId() == null ? Specification.where(spec.projectIdIsNull())
        : Specification.where(spec.projectIdEqual(request.getProjectId()));
  }

  /**
   * Task Entity に対するソート条件を生成する。
   *
   * @return
   */
  private Sort generateSort() {
    // completedAt降順 > deadlineAt昇順 > id降順 の並びになるようにソート条件を設定
    return Sort.by(Sort.Order.desc("completedAt"), Sort.Order.asc("deadlineAt"),
        Sort.Order.desc("id"));
  }
}
