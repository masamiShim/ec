## MySQL
### 接続時に怒られる
-URLのクエリパラメータを指定  
?useJDBCCompliantTimezoneShift=true&
useLegacyDatetimeCode=false&
serverTimezone=UTC

## Spring
### 起動時のデータベース周りの設定めんどい
[これで](https://codeday.me/jp/qa/20190216/269579.html)  
- `@SpringBootApplication` → `@Configuration`, `@ComponentScan`に変更　
- 設定クラスを作る
    - `@Configuration`, `@EnableAutoConfiguration`
    - `@EnableAutoConfiguration`周りはexcludeでDatabase周りの設定を指定する


### Propertyファイル読み込み
- `@PropertySource`  
これは.propertiesにしか反応しない。yamlでやりたい場合はContextInitializerで頑張る。

