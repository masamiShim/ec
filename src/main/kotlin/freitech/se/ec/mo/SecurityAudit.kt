package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import java.time.LocalDateTime

@NoArg
open class SecurityAudit(
        val created: LocalDateTime = LocalDateTime.now(),
        val createdBy: Int = 0,
        val modified: LocalDateTime = LocalDateTime.now(),
        val modifiedBy: Int = 0,
        val deleted: LocalDateTime? = null

) {
    companion object {
        fun created(id: Id): SecurityAudit {
            return SecurityAudit(LocalDateTime.now(), id.id, LocalDateTime.now(), id.id, null)
        }

        fun update(id: Id, audit: SecurityAudit): SecurityAudit {
            return SecurityAudit(audit.created, audit.createdBy, LocalDateTime.now(), id.id, audit.deleted)
        }

        fun delete(id: Id, audit: SecurityAudit): SecurityAudit {
            return SecurityAudit(audit.created, audit.createdBy, LocalDateTime.now(), id.id, LocalDateTime.now())
        }

    }
}
