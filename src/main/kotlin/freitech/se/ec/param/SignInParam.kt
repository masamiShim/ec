package freitech.se.ec.param

import freitech.se.ec.domain.enums.UserType
import freitech.se.ec.gateway.db.mo.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SignInParam() {
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var confirmation: String = ""
    var side: UserType = UserType.None

    constructor(name: String, email: String, password: String, confirmation: String, side: String) : this() {
        this.name = name
        this.email = email
        this.password = password
        this.confirmation = confirmation
        this.side = UserType.findByCode(side)
    }

    fun toUser(bCryptPasswordEncoder: BCryptPasswordEncoder): User {
        return User(nickname = name, email = email, pass = bCryptPasswordEncoder.encode(password))
    }
}
