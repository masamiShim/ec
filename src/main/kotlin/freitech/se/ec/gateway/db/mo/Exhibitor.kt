package freitech.se.ec.gateway.db.mo

import freitech.se.ec.config.NoArg
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@NoArg
data class Exhibitor(

        @OneToOne(cascade = [CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH], fetch = FetchType.LAZY, optional = true)
        @JoinTable(
                name = "rel_user_exhibitor",
                joinColumns = [JoinColumn(name = "exhibitor_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        var user: User? = null,

        @get: NotNull
        var verified: Boolean = false
) : SecurityAudit()
