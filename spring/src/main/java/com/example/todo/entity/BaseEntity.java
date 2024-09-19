package com.example.todo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

/**
 * @MappedSuperclass JPA（Java Persistence API）のアノテーション。エンティティクラスに付与するアノテーション。
 *                   このアノテーションが付与されたクラスは、エンティティクラスではなく、エンティティクラスの親クラスとして振る舞う。
 *                   つまり、このクラスはデータベースのテーブルと1対1でマッピングされない。
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // 新規作成処理前に呼び出される。
  @PrePersist
  protected void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;
  }

  // 更新処理前に呼び出される。
  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
