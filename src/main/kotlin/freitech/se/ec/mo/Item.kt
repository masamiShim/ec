package freitech.se.ec.mo

import freitech.se.ec.entity.ItemEntity


data class Item(val id: Id? = null,
                val content: ItemEntity = ItemEntity(),
                val audit: SecurityAudit = SecurityAudit()
) {
    fun update(id: Id, entity: ItemEntity): Item {
        return Item(this.id, this.content, SecurityAudit.update(id, this.audit))
    }

    fun same(id: Id): Boolean {
        return this.id == id
    }

}
