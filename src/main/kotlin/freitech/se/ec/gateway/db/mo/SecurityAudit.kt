package freitech.se.ec.mo

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*


@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class SecurityAudit(
        @javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Embedded
        open val id: Id = Id(0),

        @CreatedDate
        @Column(nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val created: LocalDateTime = LocalDateTime.now(),

        @Embedded
        @CreatedBy
        @AttributeOverride(name = "value", column = Column(name = "created_by", nullable = false))
        val createdBy: Id = Id(0),

        @LastModifiedDate
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val modified: LocalDateTime = LocalDateTime.now(),

        @Embedded
        @LastModifiedBy
        @AttributeOverride(name = "value", column = Column(name = "modified_by", nullable = false))
        val modifiedBy: Id = Id(0),

        @Column(nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val deleted: LocalDateTime? = null

)
