package freitech.se.ec.mo

import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.validation.constraints.NotBlank

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",  discriminatorType = DiscriminatorType.STRING)
abstract class BaseUser(
        override val id: Int = 0,
        @get: NotBlank
        open val email: String = "",
        @get: NotBlank
        open val password: String = "",
        @get: NotBlank
        open val type: String = ""
): SecurityAudit() {
}
