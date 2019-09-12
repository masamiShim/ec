package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@NoArg
@Entity
open class User(
        @get: NotBlank
        open val email: String = "",
        @get: NotBlank
        open val password: String = ""
        ) : SecurityAudit()
