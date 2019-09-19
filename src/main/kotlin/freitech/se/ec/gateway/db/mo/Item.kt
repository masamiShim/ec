package freitech.se.ec.gateway.db.mo

import freitech.se.ec.config.NoArg
import freitech.se.ec.domain.entity.ItemEntity
import javax.persistence.Embedded
import javax.persistence.Entity

@NoArg
@Entity
data class Item(
        @Embedded
        val content: ItemEntity = ItemEntity()
): SecurityAudit() {

    fun update(entity: ItemEntity): Item {
        return Item(content = entity)
    }

}
