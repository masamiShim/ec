## MySQL
### 接続時に怒られる
-URLのクエリパラメータを指定  
?useJDBCCompliantTimezoneShift=true&
useLegacyDatetimeCode=false&
serverTimezone=UTC

## Spring
### Propertyファイル読み込み
- `@PropertySource`  
これは.propertiesにしか反応しない。yamlでやりたい場合はContextInitializerで頑張る。
