package freitech.se.ec.gateway.db.mo

import freitech.se.ec.config.NoArg
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@NoArg
data class Exhibitor(

        /*
        @OneToOne(fetch = FetchType.LAZY, optional = true, orphanRemoval = true)
        @JoinTable(
                name = "rel_user_exhibitor",
                joinColumns = [JoinColumn(name = "exhibitor_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        */
        @OneToOne(mappedBy = "exhibitor")
        var user: User? = null,

        var verified: Boolean = false
) : SecurityAudit(), Serializable
