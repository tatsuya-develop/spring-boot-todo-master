package com.example.todo.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import com.example.todo.entity.Task;

/**
 * Task Entityに対する検索条件を生成するクラス。
 *
 * こちらをRepository層で使用することで、検索条件を動的に生成することができる。
 */
public class TaskSpecification {

  /**
   * プロジェクトが null の場合の検索条件を生成する。
   *
   * @return プロジェクトが null の場合の検索条件
   */
  public Specification<Task> projectIdIsNull() {
    // root: 検索対象のエンティティを指す。（ここでは Task Entity）
    // query: クエリの設定をするためのクエリを指す。ここでは未使用。
    // _____| 例えば、query.orderBy(builder.asc(root.get("id"))); のように、並び替え条件を設定することができる。
    // _____| 他にも、「取得フィールドの指定」や「グループ化」なども可能。
    // builder: 検索条件（WHERE句）を生成するためのビルダーを指す。
    // _______| builder.equal() や builder.isNull() など、様々な検索条件を生成するメソッドが用意されている。
    return (root, query, builder) -> builder.isNull(root.get("project"));
  }

  public Specification<Task> projectIdEqual(Integer projectId) {
    if (projectId == null) {
      return null;
    }

    return (root, query, builder) -> builder.equal(root.get("project").get("id"), projectId);
  }
}
