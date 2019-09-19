package freitech.se.ec.domain.enums

import freitech.se.ec.gateway.db.mo.User


enum class UserType(val code: String) {


    None("0") {
        override fun createUser(user: User): User {
            throw IllegalArgumentException("user type could not detected")
        }
    },
    Customer("1") {
        override fun createUser(user: User): User {
            user.customer = freitech.se.ec.gateway.db.mo.Customer()
            return user
        }
    },
    Exhibitor("2") {
        override fun createUser(user: User): User {
            user.exhibitor = freitech.se.ec.gateway.db.mo.Exhibitor()
            return user
        }
    },

    Both("3") {
        override fun createUser(user: User): User {
            user.exhibitor = freitech.se.ec.gateway.db.mo.Exhibitor()
            user.customer = freitech.se.ec.gateway.db.mo.Customer()
            return user
        }
    };

    companion object {
        fun findByCode(code: String): UserType {
            return values().firstOrNull { it.code == code } ?: None
        }
    }


    @Throws(IllegalArgumentException::class)
    abstract fun createUser(user: User): User

}
