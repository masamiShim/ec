package freitech.se.ec.usecase

import freitech.se.ec.domain.entity.ItemEntity
import freitech.se.ec.exception.IllegalPolicyException
import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.exception.SystemException
import freitech.se.ec.gateway.db.mo.Id
import freitech.se.ec.policy.item.ItemRegisterPolicy
import freitech.se.ec.service.ItemReadResult
import freitech.se.ec.service.ItemReadService
import freitech.se.ec.service.ItemWriteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class ItemUseCase {

    @Autowired
    private lateinit var itemReadService: ItemReadService

    @Autowired
    private lateinit var itemWriteService: ItemWriteService


    @Transactional
    @Throws(IllegalPolicyException::class)
    fun register(entity: ItemEntity) {

        ItemRegisterPolicy.valid(entity)

        itemWriteService.register(entity)

    }

    @Transactional
    @Throws(IllegalPolicyException::class, NotFoundException::class, SystemException::class)
    fun update(id: Id, entity: ItemEntity) {

        ItemRegisterPolicy.valid(entity)

        when (val result = itemReadService.readItem(id)) {
            is ItemReadResult.Found -> itemWriteService.update(result.item, entity)
            is ItemReadResult.NotFound -> throw result.error
            is ItemReadResult.SystemError -> throw result.error
        }
    }
}





