package freitech.se.ec.mo

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Customer(

        @OneToOne(fetch = FetchType.LAZY, optional = true)
        @JoinTable(
                name="rel_user_customer",
                joinColumns = [JoinColumn(name = "customer_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        val user: User,

        @get: NotBlank
        val name: String,

        @get: NotBlank
        val verified: Boolean)
    : SecurityAudit()
