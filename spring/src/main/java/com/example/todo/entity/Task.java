package com.example.todo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = false) // 親クラスの equals, hashCode を使わない（これを設定しないと警告が出る）
public class Task extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(length = 255, nullable = false)
  private String name;

  // priority は、0: 優先度 低, 1: 優先度 中, 2: 優先度 高 の3つの値とする
  @Column(name = "priority", nullable = false, columnDefinition = "SMALLINT")
  private Integer priority = 1; // 初期値は 優先度 中

  @Column(name = "memo", columnDefinition = "TEXT")
  private String memo = "";

  @Column(name = "deadline_at")
  private LocalDateTime deadlineAt;

  @Column(name = "completed_at")
  private LocalDateTime completedAt;
}
