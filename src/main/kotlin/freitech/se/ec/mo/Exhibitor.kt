package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@NoArg
data class Exhibitor(

        @OneToOne(fetch = FetchType.LAZY, optional = true)
        @JoinTable(
                name = "rel_user_exhibitor",
                joinColumns = [JoinColumn(name = "exhibitor_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        )
        val user: User,

        @get: NotBlank
        val name: String,
        @get: NotBlank
        val verified: Boolean
) : SecurityAudit()
