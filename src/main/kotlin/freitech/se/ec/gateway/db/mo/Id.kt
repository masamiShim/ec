package freitech.se.ec.gateway.db.mo

import freitech.se.ec.config.NoArg
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@NoArg
@Embeddable
data class Id(
        @Column(name = "id")
        val value: Long? = 0
): Serializable {
}
