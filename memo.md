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

## Stripe
### Vueコンポーネントは？
[ここで](https://tech.actindi.net/2019/06/04/084920)

## 設計まわり
### CQS
[CQS原著](https://martinfowler.com/bliki/CQRS.html)  
[原著](https://martinfowler.com/bliki/CQRS.html)  
[これ良いかも](https://www.dotnetcurry.com/patterns-practices/1461/command-query-separation-cqs)  
- Command  
値返は返さず副作用のみ
- Query  
値を返すけど副作用なし
- 図を見た感じ
    - Command(View - App(toComamndModel) - CommandModel(toDBModel) - DB)
    - Query(Infra - QueryModel(toViewModel) - App(toQueryModel) - View)
