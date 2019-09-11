package freitech.se.ec.mo

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


@EntityListeners(AuditingEntityListener::class)
abstract class SecurityAudit(
        @javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Int = 0,

        @CreatedDate
        @Column(nullable = false)
        val created: LocalDateTime = LocalDateTime.now(),

        @CreatedBy
        @Column(nullable = false)
        val createdBy: Id = Id(0),

        @LastModifiedDate
        @Column(nullable = false)
        val modified: LocalDateTime = LocalDateTime.now(),

        @LastModifiedBy
        @Column(nullable = false)
        val modifiedBy: Id = Id(0),

        val deleted: LocalDateTime? = null

)
