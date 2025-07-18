# Todoアプリケーション API仕様書

## タスク関連API

### 1. タスク一覧取得
- **エンドポイント**: `GET /api/tasks`
- **期待される振る舞い**: タスクの一覧を取得する
- **リクエスト**:
  - クエリパラメータ:
    - `projectId`: プロジェクトID（オプション）
- **レスポンス**:
  ```json
  [
    {
      "id": 1,
      "project": {
        "id": 1,
        "name": "プロジェクト名",
        "summary": "プロジェクトの概要",
        "created_at": "2024-04-06T10:00:00",
        "updated_at": "2024-04-06T10:00:00"
      },
      "parent_id": null,
      "name": "タスク名",
      "priority": {
        "name": "HIGH",
        "value": 2,
        "label": "高"
      },
      "memo": "メモ",
      "deadline_at": "2024-04-06 12:00",
      "completed_at": null,
      "created_at": "2024-04-06 10:00",
      "updated_at": "2024-04-06 10:00"
    }
  ]
  ```

### 2. タスク作成
- **エンドポイント**: `POST /api/tasks`
- **期待される振る舞い**: 新しいタスクを作成する
- **リクエスト**:
  ```json
  {
    "name": "タスク名",
    "project_id": 1,
    "priority": 1
  }
  ```
  - `name`: 必須、空文字不可
  - `project_id`: オプション
  - `priority`: 必須、0（低）、1（中）、2（高）
- **レスポンス**: 作成されたタスクの情報（タスク一覧取得のレスポンスと同じ形式）

### 3. タスク更新
- **エンドポイント**: `PUT /api/tasks`
- **期待される振る舞い**: 既存のタスクを更新する
- **リクエスト**:
  ```json
  {
    "id": 1,
    "name": "更新後のタスク名",
    "project_id": 1,
    "priority": 2,
    "memo": "更新後のメモ",
    "deadline_at": "2024-04-07T12:00:00+09:00",
    "completed_at": null
  }
  ```
  - `id`: 必須、正の整数
  - `name`: 必須、空文字不可
  - `project_id`: オプション
  - `priority`: 必須、0（低）、1（中）、2（高）
  - `memo`: オプション
  - `deadline_at`: オプション、ISO 8601形式の日時
  - `completed_at`: オプション、ISO 8601形式の日時
- **レスポンス**: 更新されたタスクの情報（タスク一覧取得のレスポンスと同じ形式）

### 4. タスク削除
- **エンドポイント**: `DELETE /api/tasks/{id}`
- **期待される振る舞い**: 指定したIDのタスクを削除する
- **リクエスト**:
  - パスパラメータ: `id`（タスクID）
- **レスポンス**: ステータスコード 204（No Content）

### 5. タスクの完了状態切り替え
- **エンドポイント**: `PUT /api/tasks/{id}/toggle`
- **期待される振る舞い**: タスクの完了状態を切り替える
- **リクエスト**:
  - パスパラメータ: `id`（タスクID）
- **レスポンス**: 更新されたタスクの情報（タスク一覧取得のレスポンスと同じ形式）

## プロジェクト関連API

### 1. プロジェクト一覧取得
- **エンドポイント**: `GET /api/projects`
- **期待される振る舞い**: プロジェクトの一覧を取得する
- **リクエスト**: なし
- **レスポンス**:
  ```json
  [
    {
      "id": 1,
      "name": "プロジェクト名",
      "summary": "プロジェクトの概要",
      "created_at": "2024-04-06T10:00:00",
      "updated_at": "2024-04-06T10:00:00"
    }
  ]
  ```

### 2. プロジェクト作成
- **エンドポイント**: `POST /api/projects`
- **期待される振る舞い**: 新しいプロジェクトを作成する
- **リクエスト**:
  ```json
  {
    "name": "プロジェクト名",
    "summary": "プロジェクトの概要"
  }
  ```
  - `name`: 必須、空文字不可
  - `summary`: オプション
- **レスポンス**: 作成されたプロジェクトの情報（プロジェクト一覧取得のレスポンスと同じ形式）

## 共通仕様

- **ベースURL**: `http://localhost:8080/api`
- **Content-Type**: `application/json`
- **CORS設定**: `http://localhost:3000`からのアクセスを許可
- **認証**: 現時点では認証なし

## エラーレスポンス

バリデーションエラーなどの場合、以下のような形式でエラーレスポンスが返されます：

```json
{
  "timestamp": "2024-04-06T10:00:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "バリデーションエラーの詳細"
}
```

