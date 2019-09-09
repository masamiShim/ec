package freitech.se.ec.mo

import java.time.LocalDateTime

open class SecurityAudit(
        val created: LocalDateTime = LocalDateTime.now(),
        val createdBy: Int? = null,
        val modified: LocalDateTime = LocalDateTime.now(),
        val modifiedBy: Int? = null,
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
