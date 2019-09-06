package freitech.se.ec.mo

import java.time.LocalDateTime

open class SecurityAudit(
        val created: LocalDateTime,
        val createdBy: Int,
        val modified: LocalDateTime,
        val modifiedBy: Int,
        val deleted: LocalDateTime?

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
