# spring-boot-todo-master
Spring Boot の ToDoサンプルアプリのマスタデータ。

## 開発環境構築
### 前提
- 以下をインストールしておくこと。
  - [Docker for Mac](https://docs.docker.com/desktop/install/mac-install/)
  - [VSCode](https://code.visualstudio.com/download)

### 1. Docker Compose を使って開発環境を作成
1. Terminal から、 `spring-boot-todo` があるディレクトリに移動する。
2. 以下、 Terminal操作
    ```bash
    # Dockerイメージのビルド ＆ コンテナ作成
    ## 以下コマンド実行後、コンテナ立ち上げ処理が走るので終了まで待つ。
    $ docker compose up -d --build
    ```

### 2. VSCodeの準備
1. VSCodeの拡張機能から、`Dev containers` を検索し、インストールする。

### 3. `Dev Containers` を使ってコンテナ上のソースコードを開く

### 4. `Dev Containers` を使って、コンテナ上のソースを開く
1. サイドバーに、`リモート エクスプローラー` のアイコンがあるので、それをクリックする。
2. 出てきたコンテナ一覧から、`spring-boot-todo spring-boot-todo-java-1` というものを見つけ `→` の右隣の「フォルダ追加」っぽいマークをクリックする。
3. 新しいウィンドウで、コンテナ上のSpringコードが開く
    - コンテナ作成後に初めて接続する場合、拡張機能のインストール等が始まるため、下のプログレスバーを見て、インストールが完了するかを確認しておくこと。

### 5. Springを起動してみる
1. サイドバーに、「実行とデバッグ」があるので、それをクリックする。
2. Spring Boot Application を実行。

## テーブル定義

### projects

| カラム名      | 型           | 必須   | 説明                    |
| ------------- | ------------ | ------ | ----------------------- |
| id            | 整数         | 必須   | プロジェクトの一意なID   |
| name          | 文字列(255)  | 必須   | プロジェクト名           |
| summary       | テキスト     | 任意   | プロジェクトの概要       |
| created_at    | タイムスタンプ| 必須   | プロジェクト作成日時     |
| updated_at    | タイムスタンプ| 必須   | プロジェクト更新日時     |
| deleted_at    | タイムスタンプ| 任意   | プロジェクト削除日時     |

### tasks

| カラム名      | 型           | 必須   | 説明                    |
| ------------- | ------------ | ------ | ----------------------- |
| id            | 整数         | 必須   | タスクの一意なID         |
| name          | 文字列(255)  | 必須   | タスク名                 |
| project_id    | 整数         | 任意   | 紐づけられたプロジェクトのID |
| parent_id     | 整数         | 任意   | 親タスクのID（サブタスクがある場合）|
| priority      | 整数         | 必須   | タスクの優先度（列挙型）  |
| memo          | テキスト     | 任意   | タスクのメモ             |
| deadline_at   | 日時         | 任意   | タスクの締め切り日時     |
| completed_at  | 日時         | 任意   | タスクの完了日時         |
| created_at    | タイムスタンプ| 必須   | タスク作成日時           |
| updated_at    | タイムスタンプ| 必須   | タスク更新日時           |

### tags

| カラム名      | 型           | 必須   | 説明                    |
| ------------- | ------------ | ------ | ----------------------- |
| id            | 整数         | 必須   | タグの一意なID           |
| name          | 文字列(255)  | 必須   | タグ名                   |
| summary       | テキスト     | 任意   | タグの概要               |
| created_at    | タイムスタンプ| 必須   | タグ作成日時             |
| updated_at    | タイムスタンプ| 必須   | タグ更新日時             |

### task_tags

| カラム名      | 型           | 必須   | 説明                    |
| ------------- | ------------ | ------ | ----------------------- |
| id            | 整数         | 必須   | タスクタグの一意なID     |
| task_id       | 整数         | 必須   | 紐づけられたタスクのID   |
| tag_id        | 整数         | 必須   | 紐づけられたタグのID     |
| created_at    | タイムスタンプ| 必須   | タスクタグ作成日時       |
| updated_at    | タイムスタンプ| 必須   | タスクタグ更新日時       |

## よく使うコマンド

### Docker Compose

```bash

## docker compose の起動
docker compose up -d --build

## docker compose の再起動
docker compose restart

## docker compose の停止
docker compose stop

## docker compose の終了（コンテナ停止 + コンテナ削除）
docker compose down

# docker compose の起動状況確認
docker compose ps -a

# docker compose のコンテナ内にアクセス（SERVICE には、 `java`, `mysql` といった、docker-compose.yml のサービス名が入る
docker compose exec {SERVICE} bash
```

### DB接続情報

- /docker/mysql/db.env に格納済

### うまくテストが実行できないときに試すこと

```
sh gradlew clean
sh gradlew build --refresh-dependencies
```