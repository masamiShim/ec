package freitech.se.ec.param

import freitech.se.ec.enums.UserType
import freitech.se.ec.mo.User

class SignInParam() {
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var side: UserType = UserType.None

    constructor(name: String, email: String, password: String, side: String) : this() {
        this.name = name
        this.email = email
        this.password = password
        this.side = UserType.findByCode(side)
    }

    fun toUser(): User {
        return side.createUser(email, name, password)
    }
}
