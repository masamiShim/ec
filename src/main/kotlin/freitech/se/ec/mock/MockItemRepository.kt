package freitech.se.ec.mock

import freitech.se.ec.entity.ItemEntity
import freitech.se.ec.exception.IllegalStoreOperation
import freitech.se.ec.mo.Id
import freitech.se.ec.mo.Item
import freitech.se.ec.mo.SecurityAudit

object MockItemRepository {
    var items: List<Item> = mutableListOf()

    var idSupplier: IdSupplier = IdSupplier()

    fun add(entity: ItemEntity) {
        val id = idSupplier.createId()
        val item = Item(id, entity, SecurityAudit.created(id))
        val _tmp = items.toMutableList()
        _tmp.add(item)
        this.items = _tmp.toList()
    }

    fun update(itemId: Id, entity: ItemEntity, updatedById: Id) {

        val target: Item = this.items.find { it.id.id == itemId.id }
                .let { item -> item?.update(updatedById, entity) }
                ?: throw IllegalStoreOperation("update target not found.")

        this.items = this.items.map { if (it.id.id == itemId.id) target else it }
    }
}
