package freitech.se.ec.vo

class Email(val value: String) {
    init {
        require(Regex(""""*@*""").matches(value)) { "メールアドレスの形式が。不正です" }
    }
}
