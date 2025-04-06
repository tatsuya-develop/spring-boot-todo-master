package com.example.todo.dto.request.projects;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Getter Lombokのアノテーション。クラス内の全てのフィールドに対して、getterメソッドを自動生成する。
 *
 * @RequiredArgsConstructor Lombokのアノテーション。フィールドを引数に持つコンストラクタを自動生成する。
 */
@Getter
@RequiredArgsConstructor
public class ProjectCreateRequest {

  /**
   * @NotEmpty 引数がnullまたは空文字の場合に、エラーメッセージを返すバリデーションアノテーション。未入力でリクエストされたくない文字列型のフィールドに対して付与する。
   */
  @NotEmpty
  private final String name;

  private final String summary;
}
