## 開発環境構築手順
### 前提

- Eclipse を使った開発を想定しています。

### 事前準備

- [ ] Eclipse を立ち上げて、以下 2点を確認し、メモしておきます。（メモ帳アプリに貼り付けておくとか）
  - Eclipse ワークスペースの パス
    - Eclipse を立ち上げると「Eclipse IDE ランチャー」が表示されて、そこにワークスペースのパスが表示されます。
  - `eclipse > 設定 > Java > インストール済みのJRE` を開き、 `Java21` のロケーションを確認
    - 例） `/Applications/Eclipse_2025-06.app/Contents/java/21`
- [ ] GitHub Classroom への参加
  - https://classroom.github.com/a/jm-M0kEF のリンクを踏んで、GitHub Classroom に参加してください。
- [ ] https://volta.sh/ をインストールすること
  - `Try it out!` に書いてることをそのままやればOKだと思う。
    - もし Node のインストールが必要な場合や、うまく動かない場合は、 `volta install node@v20` もやっておくと良いかも。

### リポジトリのclone

```bash
# 1. Eclipse ワークスペースに移動。
#    {Eclipse ワークスペースのパス} は、事前準備で取得したパスを選んでください。
cd {Eclipse ワークスペースのパス}

# 2. リポジトリのクローン
#    GitHub Classroom で作成されたリポジトリのページにアクセスし、Code > SSH からコピーしてください。
git clone git@github.com:VT-Java/spring-boot-todo-master-{GitHubのユーザ名}.git
```
****
### Eclipse にプロジェクトを読み込ませる

```bash
# 1. pwd で現在のディレクトリを確認
#    現在位置が、Eclipse ワークスペースのパス であればOK。
pwd

# 2. クローンしたディレクトリに移動
cd spring-boot-todo-master-{GitHubアカウント名}

# 3. 事前準備で確認した Java21 のロケーションパスを使って、Eclipse プロジェクトに対応させる
JAVA_HOME="Java21 ロケーション" ./gradlew eclipse

# 以下のように出力されればOK
Starting a Gradle Daemon, 1 incompatible Daemon could not be reused, use --status for details

BUILD SUCCESSFUL in 4s
3 actionable tasks: 3 executed
```

ここまで終わったらEclipse に戻り、 [パッケージ・エクスプローラー]のウィンドウの中で、 `右クリック > インポート > 一般 > 既存プロジェクトをワークスペースへ` を選択。

ルート・ディレクトリーの選択で、 `spring-boot-todo-master-{GitHubアカウント名}` を選択し、完了。

### React アプリのセットアップ

```bash
# 1. pwd で現在のディレクトリを確認
#    現在位置が、spring-boot-todo-master-{GitHubアカウント名} であればOK。
pwd

# 2. React ディレクトリに移動
cd react

# 3. npm install を実行し、React App を動かすのに必要なライブラリをインストールする
npm i

# 4. React ローカルサーバーを立ち上げる。
#    ＊今後、TODOアプリを動かしたい場合は、このローカルサーバーの立ち上げも実施すること
npm run dev

# 以下のようになればOK
> app@0.0.0 dev
> vite


  VITE v5.4.2  ready in 166 ms

  ➜  Local:   http://localhost:3000/
  ➜  Network: http://192.168.11.49:3000/
  ➜  press h + enter to show help

# その後、http://localhost:3000/ にアクセスすると、ToDoアプリが立ち上がっていることを確認できる。
# 同時にSpring の起動をしていないと、画面は出ていても何もデータは表示されない and 登録もできない。
```

### Spring Boot の起動

1. Eclipse の パッケージ・エクスプローラーから、 `spring-boot-todo-master-{GitHubアカウント名}` を右クリック。
2. [実行] > [Spring Boot アプリケーション] を選択。
3. 以下のような出力がされればOK。
   ```bash
    .   ____          _            __ _ _
    /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
    \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
    '  |____| .__|_| |_|_| |_\__, | / / / /
    =========|_|==============|___/=/_/_/_/

    [32m :: Spring Boot :: [39m              [2m (v3.4.4)[0;39m

    [2m2025-07-11T23:08:48.413+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.example.todo.TodoApplication        [0;39m [2m:[0;39m Starting TodoApplication using Java 21.0.7 with PID 40473 (/Users/user_name/workspace/eclipse-projects/spring-boot-todo-master/build/classes/java/main started by user_name in /Users/user_name/workspace/eclipse-projects/spring-boot-todo-master)
    [2m2025-07-11T23:08:48.414+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.example.todo.TodoApplication        [0;39m [2m:[0;39m No active profile set, falling back to 1 default profile: "default"
    [2m2025-07-11T23:08:48.437+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36m.e.DevToolsPropertyDefaultsPostProcessor[0;39m [2m:[0;39m Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
    [2m2025-07-11T23:08:48.437+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36m.e.DevToolsPropertyDefaultsPostProcessor[0;39m [2m:[0;39m For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
    [2m2025-07-11T23:08:48.794+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Bootstrapping Spring Data JPA repositories in DEFAULT mode.
    [2m2025-07-11T23:08:48.829+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Finished Spring Data repository scanning in 30 ms. Found 2 JPA repository interfaces.
    [2m2025-07-11T23:08:49.135+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.b.w.embedded.tomcat.TomcatWebServer [0;39m [2m:[0;39m Tomcat initialized with port 8080 (http)
    [2m2025-07-11T23:08:49.144+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.apache.catalina.core.StandardService  [0;39m [2m:[0;39m Starting service [Tomcat]
    [2m2025-07-11T23:08:49.144+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.apache.catalina.core.StandardEngine   [0;39m [2m:[0;39m Starting Servlet engine: [Apache Tomcat/10.1.39]
    [2m2025-07-11T23:08:49.164+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.a.c.c.C.[Tomcat].[localhost].[/]      [0;39m [2m:[0;39m Initializing Spring embedded WebApplicationContext
    [2m2025-07-11T23:08:49.164+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mw.s.c.ServletWebServerApplicationContext[0;39m [2m:[0;39m Root WebApplicationContext: initialization completed in 727 ms
    [2m2025-07-11T23:08:49.250+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.hibernate.jpa.internal.util.LogHelper [0;39m [2m:[0;39m HHH000204: Processing PersistenceUnitInfo [name: default]
    [2m2025-07-11T23:08:49.283+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36morg.hibernate.Version                   [0;39m [2m:[0;39m HHH000412: Hibernate ORM core version 6.6.11.Final
    [2m2025-07-11T23:08:49.302+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.h.c.internal.RegionFactoryInitiator   [0;39m [2m:[0;39m HHH000026: Second-level cache disabled
    [2m2025-07-11T23:08:49.471+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.o.j.p.SpringPersistenceUnitInfo     [0;39m [2m:[0;39m No LoadTimeWeaver setup: ignoring JPA class transformer
    [2m2025-07-11T23:08:49.489+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Starting...
    [2m2025-07-11T23:08:49.604+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.zaxxer.hikari.pool.HikariPool       [0;39m [2m:[0;39m HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:spring_boot_todo user=SA
    [2m2025-07-11T23:08:49.605+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Start completed.
    [2m2025-07-11T23:08:49.620+09:00[0;39m [33m WARN[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36morg.hibernate.orm.deprecation           [0;39m [2m:[0;39m HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
    [2m2025-07-11T23:08:49.633+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36morg.hibernate.orm.connections.pooling   [0;39m [2m:[0;39m HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
    [2m2025-07-11T23:08:50.259+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.h.e.t.j.p.i.JtaPlatformInitiator      [0;39m [2m:[0;39m HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
    [2m2025-07-11T23:08:50.299+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mj.LocalContainerEntityManagerFactoryBean[0;39m [2m:[0;39m Initialized JPA EntityManagerFactory for persistence unit 'default'
    [2m2025-07-11T23:08:50.460+09:00[0;39m [33m WARN[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mJpaBaseConfiguration$JpaWebConfiguration[0;39m [2m:[0;39m spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
    [2m2025-07-11T23:08:50.475+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.b.a.w.s.WelcomePageHandlerMapping   [0;39m [2m:[0;39m Adding welcome page template: index
    [2m2025-07-11T23:08:50.639+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.b.a.h2.H2ConsoleAutoConfiguration   [0;39m [2m:[0;39m H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:spring_boot_todo'
    [2m2025-07-11T23:08:50.670+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.b.d.a.OptionalLiveReloadServer      [0;39m [2m:[0;39m LiveReload server is running on port 35729
    [2m2025-07-11T23:08:50.691+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mo.s.b.w.embedded.tomcat.TomcatWebServer [0;39m [2m:[0;39m Tomcat started on port 8080 (http) with context path '/'
    [2m2025-07-11T23:08:50.698+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [  restartedMain] [0;39m[36mcom.example.todo.TodoApplication        [0;39m [2m:[0;39m Started TodoApplication in 2.49 seconds (process running for 2.864)
    [2m2025-07-11T23:08:55.269+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [nio-8080-exec-1] [0;39m[36mo.a.c.c.C.[Tomcat].[localhost].[/]      [0;39m [2m:[0;39m Initializing Spring DispatcherServlet 'dispatcherServlet'
    [2m2025-07-11T23:08:55.269+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Initializing Servlet 'dispatcherServlet'
    [2m2025-07-11T23:08:55.271+09:00[0;39m [32m INFO[0;39m [35m40473[0;39m [2m--- [nio-8080-exec-1] [0;39m[36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Completed initialization in 2 ms
   ```

### テストの実行

```bash
# 1. pwd で現在のディレクトリを確認
#    現在位置が、spring-boot-todo-master-{GitHubアカウント名} であればOK。
pwd

# 2. テストを実行
./gradlew test
```

### Spring Boot が何か不調なときは...

```bash
# 1. pwd で現在のディレクトリを確認
#    現在位置が、spring-boot-todo-master-{GitHubアカウント名} であればOK。
pwd

# 2. clean を実行
./gradlew clean
```

### DBの確認

1. Spring Boot を立ち上げておく。
2. localhost:8080/h2-console にアクセス。
3. ログイン画面が表示されるので、JDBC URLの部分を以下に書き換えたあとに Connect
   1. `jdbc:h2:mem:spring_boot_todo;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
4. 接続成功
