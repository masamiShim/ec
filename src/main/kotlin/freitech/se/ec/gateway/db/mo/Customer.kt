package freitech.se.ec.gateway.db.mo

import javax.persistence.*

@Entity
data class Customer(

        @OneToOne(cascade = [CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH], fetch = FetchType.LAZY, optional = true)
        @JoinTable(
                name = "rel_user_customer",
                joinColumns = [JoinColumn(name = "customer_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        var user: User? = null,

        var verified: Boolean = false)
    : SecurityAudit()
