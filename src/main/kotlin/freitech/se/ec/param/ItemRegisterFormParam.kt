package freitech.se.ec.param

import freitech.se.ec.domain.entity.ItemEntity
import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.gateway.db.mo.Id
import freitech.se.ec.vo.item.Price
import freitech.se.ec.vo.item.Quantity

/**
 * BadRequestException
 */
class ItemRegisterFormParam(
        private val exhibitorId: String,
        private val name: String,
        private val code: String,
        private val price: String,
        private val quantity: String,
        private val comment: String
) {

   @Throws(BadRequestException::class)
    fun toItemEntity(): ItemEntity {
        val exhibitorId = try {
            Id(exhibitorId.toLong())
        } catch (e: Exception) {
            throw BadRequestException(e.message ?: "illegal value exhibitorId")
        }

        val name: String = name
        val code: String = code
        val price: Price = try {
            Price(price)
        } catch (e: Exception) {
            throw BadRequestException(e.message ?: "illegal value price")
        }

        val quantity: Quantity = try {
            Quantity(quantity)
        } catch (e: Exception) {
            throw BadRequestException(e.message ?: "illegal value quantity")
        }

        val comment: String = comment

        return ItemEntity(exhibitorId, name, code, price, quantity, comment)
    }
}
