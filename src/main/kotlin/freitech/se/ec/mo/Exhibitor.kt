package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.validation.constraints.NotBlank

@Entity
@NoArg
data class Exhibitor(

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id")
        val user: User,

        @get: NotBlank
        val name: String,
        @get: NotBlank
        val verified: Boolean
        ) : User()
