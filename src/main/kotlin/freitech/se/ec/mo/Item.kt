package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import freitech.se.ec.entity.ItemEntity

@NoArg
data class Item(val id: Id = Id(),
                val content: ItemEntity = ItemEntity(),
                val audit: SecurityAudit = SecurityAudit()
) {
    fun update(id: Id, entity: ItemEntity): Item {
        return Item(this.id, entity, SecurityAudit.update(id, this.audit))
    }

    fun same(id: Id): Boolean {
        return this.id == id
    }

}
