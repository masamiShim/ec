package freitech.se.ec.gateway.db.mo

import freitech.se.ec.config.NoArg
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@NoArg
@Embeddable
@Deprecated("Longラッパーの独自型で自動採番しようと")
data class Id(
        @Column(name = "id")
        val value: Long? = 0
): Serializable {
}
