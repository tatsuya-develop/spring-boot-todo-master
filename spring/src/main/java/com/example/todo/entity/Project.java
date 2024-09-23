package com.example.todo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Data Lombokのアノテーション。クラス内の全てのフィールドに対して、getter、setter、toString、equals、hashCodeなどのメソッドを自動生成する。
 *       このアノテーションが付与されたクラスは、データクラスとして振る舞う。
 *
 * @Entity JPA（Java Persistence API）のアノテーション。エンティティクラスであることを示す。
 *         このアノテーションが付与されたクラスは、データベースのテーブルと1対1でマッピングされる。
 *
 * @Table エンティティクラスに付与するアノテーション。テーブル名を指定する。
 */
@Data
@Entity
@Table(name = "projects")
@EqualsAndHashCode(callSuper = false) // 親クラスの equals, hashCode を使わない（これを設定しないと警告が出る）
public class Project extends BaseEntity {

  /**
   * @Id JPA（Java Persistence API）のアノテーション。エンティティの主キーであることを示す。
   *     このアノテーションが付与されたフィールドは、データベースのテーブルにおいて主キーとして扱われる。
   *
   * @GeneratedValue JPA（Java Persistence API）のアノテーション。主キーの生成方法を指定する。
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * @Column エンティティクラスのフィールドに付与するアノテーション。カラムに関する設定を行う。
   *         nullable属性には、カラムにNULLを許可するかどうかを指定する。nullable属性を指定しない場合、デフォルトはtrue（NULLを許可する）となる。
   *         length属性には、カラムの長さを指定する。
   */
  @Column(nullable = false, length = 255)
  private String name;

  /**
   * @Column エンティティクラスのフィールドに付与するアノテーション。カラムに関する設定を行う。
   *         columnDefinition属性には、カラムの定義を直接指定する。データ型やNULL制約、デフォルト値などを指定することができる。
   */
  @Column(columnDefinition = "TEXT")
  private String summary;

  /**
   * @Column エンティティクラスのフィールドに付与するアノテーション。カラムに関する設定を行う。
   *         name属性には、カラム名を指定する。この属性を指定しない場合、フィールド名がカラム名として使用される。
   */
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;
}
