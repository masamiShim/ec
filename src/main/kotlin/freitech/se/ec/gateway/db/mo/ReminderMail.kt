package freitech.se.ec.gateway.db.mo

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.ManyToOne

class ReminderMail(
        var url: String,
        @ManyToOne(optional = false, cascade = [CascadeType.ALL])
        val user: User,
        val code: String,
        val expired: LocalDateTime = LocalDateTime.now().plusHours(2)
) : SecurityAudit() {
    init {
        url = url + code
    }
}

