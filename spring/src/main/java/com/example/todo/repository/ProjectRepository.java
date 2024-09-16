package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.entity.Project;

/**
 * JpaRepository Spring Data JPAのリポジトリクラス。このインターフェースを継承することで、CRUD操作を行うメソッドを自動生成する。
 * JpaRepositoryインターフェースは、データベースのテーブルに対してCRUD操作を行うためのメソッドを提供する。
 * JpaRepositoryインターフェースを継承することで、データベースのテーブルに対して、データの追加(C)、取得(R)、更新(U)、削除(D)などの操作を行うことができる。
 *
 * @see https://qiita.com/__x__/items/7e0003a949ce4c6cedd0 は参考になりそう。
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
