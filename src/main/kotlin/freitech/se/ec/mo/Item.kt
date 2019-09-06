package freitech.se.ec.mo

import freitech.se.ec.entity.ItemEntity


data class Item(val id: Id,
                val content: ItemEntity,
                val audit: SecurityAudit
) {

    fun update(id: Id, entity: ItemEntity): Item {
        return Item(this.id, this.content, SecurityAudit.update(id, this.audit))
    }

    fun same(id: Id): Boolean {
        return this.id == id
    }

}
