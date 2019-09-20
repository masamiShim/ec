package freitech.se.ec.response.app.item

import freitech.se.ec.domain.entity.ItemEntity
import freitech.se.ec.gateway.db.mo.Item

data class ItemResponse(val itemEntity: ItemEntity) {
    companion object {
        fun from(item: Item): ItemResponse {
            return ItemResponse(item.content)
        }
    }
}
