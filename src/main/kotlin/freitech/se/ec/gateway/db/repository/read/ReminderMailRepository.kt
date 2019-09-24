package freitech.se.ec.gateway.db.repository.read

import freitech.se.ec.gateway.db.mo.ReminderMail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReminderMailRepository : JpaRepository<ReminderMail, Long> {
    fun findByUrlAndExpired(url: String, expired: LocalDateTime): ReminderMail?
}
