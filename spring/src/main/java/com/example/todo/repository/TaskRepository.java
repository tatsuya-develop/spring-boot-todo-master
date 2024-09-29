package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.todo.entity.Task;

/**
 * Task Entity に対するデータ操作を行うためのリポジトリインターフェイス。
 *
 * JpaRepository を継承することで、データの追加・更新・削除などの基本的な操作を簡単に実装することができる。
 *
 * JpaSpecificationExecutor を継承することで、動的な検索条件を生成するためのメソッドを提供する。
 */
public interface TaskRepository
    extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

}
