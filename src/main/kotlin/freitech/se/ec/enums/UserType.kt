package freitech.se.ec.enums

import freitech.se.ec.mo.Customer
import freitech.se.ec.mo.Exhibitor
import freitech.se.ec.mo.User

enum class UserType(val code: String) {


    None("0") {
        override fun createUser(email: String, name: String, password: String): User {
            throw IllegalArgumentException("user type could not detected")
        }
    },
    Cutomer("1") {
        override fun createUser(email: String, name: String, password: String): User {
            return User(nickname = name, email = email, pass = password, customer = Customer())
        }
    },
    Exhibitor("2") {
        override fun createUser(email: String, name: String, password: String): User {
            return User(nickname = name, email = email, pass = password, exhibitor = Exhibitor())
        }
    };

    companion object {
        fun findByCode(code: String): UserType {
            return values().firstOrNull { it.code == code } ?: None
        }
    }


    @Throws(IllegalArgumentException::class)
    abstract fun createUser(email: String, name: String, password: String): User

}
