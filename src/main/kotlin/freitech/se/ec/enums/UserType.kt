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
            return User(email = email, pass = password, customer = Customer(name = name))
        }
    },
    Exhibitor("2") {
        override fun createUser(email: String, name: String, password: String): User {
            return User(email = email, pass = password, exhibitor = Exhibitor(name = name))
        }
    };

    companion object {
        fun findByCode(code: String): UserType {
           return values().firstOrNull { it.code == code } ?: None
        }
    }

    abstract fun createUser(email: String, name: String, password: String): User

}
