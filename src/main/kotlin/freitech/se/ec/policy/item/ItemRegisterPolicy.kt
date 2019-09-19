package freitech.se.ec.policy.item

import freitech.se.ec.domain.entity.ItemEntity
import freitech.se.ec.exception.IllegalPolicyException

class ItemRegisterPolicy() {
    companion object {
        @Throws(IllegalPolicyException::class)
        fun valid(entity: ItemEntity){

            if(!entity.price.greaterThanZero()){
                throw IllegalPolicyException("price not set less than equal zero")
            }

            if(!entity.quantity.greaterThanZero()){
                throw IllegalPolicyException("quantity not set less than equal zero")
            }

        }
    }
}
