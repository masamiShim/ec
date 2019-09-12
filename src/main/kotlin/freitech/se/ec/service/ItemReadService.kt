package freitech.se.ec.service

import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.exception.SystemException
import freitech.se.ec.mo.Id
import freitech.se.ec.mo.Item
import freitech.se.ec.repository.read.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemReadService {
    @Autowired
    private lateinit var itemRepository: ItemRepository

    fun readItem(id: Id): ItemReadResult = try {
        val item: Item = itemRepository.findById(id)
                .orElseThrow(RuntimeException("item not found")::class::objectInstance)
        ItemReadResult.Found(item)
    } catch (e: NotFoundException) {
        ItemReadResult.NotFound(NotFoundException(e.message!!))
    } catch (e: Exception) {
        ItemReadResult.SystemError(SystemException(e.message ?: "system error occurred"))
    }
}


sealed class ItemReadResult {
    data class Found(val item: Item) : ItemReadResult()
    data class NotFound(val error: NotFoundException) : ItemReadResult()
    data class SystemError(val error: SystemException) : ItemReadResult()
}
