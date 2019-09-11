package freitech.se.ec.mo

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@Entity
@DiscriminatorValue("customer")
data class Customer(
        @get: NotBlank
        val name: String,
        @get: NotBlank
        override val email: String,
        @get: NotBlank
        override val password: String,
        @get: NotBlank
        val verified: Boolean)
    : BaseUser(), User {

    override fun getPass(): String {
        return password
    }

    override fun getUserName(): String {
        return email
    }
}
