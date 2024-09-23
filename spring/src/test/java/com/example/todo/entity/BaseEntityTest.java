package com.example.todo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityTest {

  /**
   * BaseEntity の単体テストのために用意された MockEntity クラス。
   *
   * BaseEntity は抽象クラスであるため、そのままインスタンス化することができない。
   *
   * そのため、Mock として利用するための一時的なクラスを用意し、これに継承させてテストをする。
   */
  class MockEntity extends BaseEntity {
  }

  protected MockEntity mockEntity;

  @BeforeEach
  void setUp() {
    this.mockEntity = new MockEntity();
  }

  /**
   * BaseEntity の onCreateメソッドのテストを行うクラス。
   *
   * ネストされるクラスについては、static 修飾子を付与することで、外部クラス（BaseEntityTest）のインスタンスを生成せずにテストを行うことができる。
   */
  static class TestOnCreate extends BaseEntityTest {

    @Test
    void onCreateメソッドが呼び出されたときにcreatedAtとupdatedAtが設定されること() {
      // LocalDateTime.now() のモック化
      // LocalDateTime.now() が呼び出されたときに返す LocalDateTime オブジェクトを設定する
      // LocalDateTime.now() は static メソッドであるため、Mockito.mockStatic() を利用してモック化する
      // static メソッドのモック化は、try-with-resources 構文を利用して、モック化した範囲を限定する必要がある。
      // そうしないと、モック化した static メソッドが他のテストに影響を及ぼす可能性があるため。
      try (MockedStatic<LocalDateTime> mockedLocalDateTime =
          Mockito.mockStatic(LocalDateTime.class)) {
        LocalDateTime time = LocalDateTime.of(2024, 9, 1, 10, 0, 0);
        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(time);

        this.mockEntity.onCreate();

        assertEquals(time, this.mockEntity.getCreatedAt());
        assertEquals(time, this.mockEntity.getUpdatedAt());
      }
    }
  }

  static class TestOnUpdate extends BaseEntityTest {

    @Test
    void onUpdateメソッドが呼び出されたときにupdatedAtが設定されること() {
      try (MockedStatic<LocalDateTime> mockedLocalDateTime =
          Mockito.mockStatic(LocalDateTime.class)) {

        LocalDateTime defaultTime = LocalDateTime.of(2024, 9, 1, 10, 0, 0);
        this.mockEntity.setCreatedAt(defaultTime);
        this.mockEntity.setUpdatedAt(defaultTime);

        LocalDateTime updatedTime = LocalDateTime.of(2024, 9, 2, 10, 0, 0);

        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(updatedTime);

        this.mockEntity.onUpdate();

        assertEquals(defaultTime, this.mockEntity.getCreatedAt());
        assertEquals(updatedTime, this.mockEntity.getUpdatedAt());
      }
    }
  }
}
