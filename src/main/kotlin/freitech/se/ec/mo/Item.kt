package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import freitech.se.ec.entity.ItemEntity
import javax.persistence.Embedded
import javax.persistence.Entity

@NoArg
@Entity
data class Item(
        override val id: Int = 0,
        @Embedded
        val content: ItemEntity = ItemEntity()
): SecurityAudit() {

    fun update(entity: ItemEntity): Item {
        return Item(id = id, content = entity)
    }

}
