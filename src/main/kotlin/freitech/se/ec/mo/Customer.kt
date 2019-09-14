package freitech.se.ec.mo

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Customer(

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = true)
        @JoinTable(
                name = "rel_user_customer",
                joinColumns = [JoinColumn(name = "customer_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        val user: User? = null,

        @get: NotNull
        val verified: Boolean = false)
    : SecurityAudit()
