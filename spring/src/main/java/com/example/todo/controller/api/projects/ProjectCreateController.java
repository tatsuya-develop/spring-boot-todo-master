package com.example.todo.controller.api.projects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectCreateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @RestController Spring MVCのコントローラとして振る舞うクラスに付与するアノテーション。
 *                 このアノテーションが付与されたクラスは、HTTPリクエストを受け取るコントローラとして動作する。
 *                 また、JSONやXMLなどのデータをHTTPレスポンスとして返却することができる。
 *
 * @RequestMapping クラスまたはメソッドに対してHTTPリクエストのパスやメソッドタイプ（GET、POSTなど）をマッピングするためのアノテーション。
 *                 第一引数に、リクエストされるURLパスを指定する。
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectCreateController {
  private final ProjectCreateService projectCreateService;

  /**
   * コンストラクタインジェクション（DI）
   *
   * コンストラクタインジェクション（DI）とは、クラスのコンストラクタを使用して、依存性の注入を行う方法のこと。
   * 依存性の注入とは、クラス間の依存関係を外部から注入することで、クラス間の結合度を低くし、柔軟な設計を実現するための手法。
   * 実際の動きとしては、コンストラクタの引数にインターフェースを指定し、そのインターフェースを実装したクラスのインスタンスを渡すことで、依存性の注入を行う。
   *
   * DI に関する Q&A
   *
   * DIされたインスタンスは、どのタイミングで生成されたもの？ → Spring Frameworkによって、アプリケーションの起動時に生成される。
   * DIされたインスタンスは、どのタイミングで破棄されるもの？ → Spring Frameworkによって、アプリケーションの終了時に破棄される。
   *
   * @param projectCreateService
   */
  public ProjectCreateController(ProjectCreateService projectCreateService) {
    // コンストラクタインジェクションで受け取ったProjectCreateServiceのインスタンスを、フィールドに代入する。
    this.projectCreateService = projectCreateService;
  }

  /**
   * @PostMapping HTTP POSTリクエストを受け取るメソッドに付与するアノテーション。このアノテーションが付与されたメソッドは、HTTP
   *              POSTリクエストを受け取るコントローラのメソッドとして動作する。
   *
   * @Valid メソッドの引数に付与するアノテーション。引数のオブジェクトに対してバリデーションを行う。
   *        バリデーションエラーが発生した場合、メソッドの実行を中断し、エラーメッセージを返却する。このアノテーションを付与することで、コントローラ内でバリデーションを行うことができる。
   *
   * @RequestBody HTTPリクエストのボディ部分を受け取るメソッドの引数に付与するアノテーション。リクエストボディを受け取るために使用する。
   *              リクエストボディとは、HTTPリクエストのボディ部分に含まれるJSONやXMLなどのデータのこと。
   *
   */
  @PostMapping
  public ResponseEntity<Project> invoke(@Valid @RequestBody ProjectCreateRequest request) {
    Project project = this.projectCreateService.invoke(request);

    // okメソッドを使用して、HTTPステータス200を返却する。
    // また、作成したProjectオブジェクトをJSON形式で返却する。
    return ResponseEntity.ok(project);
  }
}
