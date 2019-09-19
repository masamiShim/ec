package freitech.se.ec.service

import freitech.se.ec.domain.entity.ItemEntity
import freitech.se.ec.gateway.db.mo.Item
import freitech.se.ec.gateway.db.repository.read.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemWriteService {
    @Autowired
    private lateinit var itemRepository: ItemRepository

    fun register(value: ItemEntity) {
        val item = Item(content = value)
        itemRepository.save(item)
    }

    fun update(prev: Item, value: ItemEntity) {
        val item = prev.update(value)
        itemRepository.save(item)
    }
}
