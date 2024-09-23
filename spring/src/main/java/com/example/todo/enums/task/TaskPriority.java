package com.example.todo.enums.task;

import lombok.Getter;
import java.util.Arrays;

/**
 * タスクの優先度を表す列挙型（enum）
 *
 * enumとは、列挙型（enumeration type）のことで、特定の値の中から1つを選択するための型。
 */
@Getter
public enum TaskPriority {
  // 低, 中, 高 の3つの値を持つ
  // key: value の関係性として、LOW: 0, MEDIUM: 1, HIGH: 2 とする。
  // +α として、それぞれの値に対応するラベルも持つ。（例: LOW のラベルは "低"）
  LOW(0, "低"), MEDIUM(1, "中"), HIGH(2, "高");

  private final int value;
  private final String label;

  TaskPriority(int value, String label) {
    this.value = value;
    this.label = label;
  }

  /**
   * 値からTaskPriorityを取得する
   *
   * @param value 優先度の値
   * @return TaskPriority
   */
  public static TaskPriority fromValue(int value) {
    // 処理の順番は以下。
    // 1. TaskPriority.values() で、TaskPriorityの全ての値を取得し、Streamに変換する。
    // 2. filter() で、引数の値と一致するTaskPriorityを取得する。
    // 3. findFirst() で、取得したTaskPriorityの中で最初の要素を取得する。
    // 4. orElseThrow() で、TaskPriorityが取得できなかった場合に例外をスローする。取得できた場合はその値を返す。
    return Arrays.stream(TaskPriority.values()).filter(priority -> priority.getValue() == value)
        .findFirst().orElseThrow(() -> new IllegalArgumentException("Unexpected value: " + value));
  }
}
