package freitech.se.ec.param

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.vo.item.Price
import freitech.se.ec.vo.item.Quantity

/**
 * BadRequestException
 */
class ItemRegisterFormParam(name: String, code: String, price: String, quantity: String, comment: String) {
    val name: String = name
    val code: String = code
    val price: Price = try {
        Price(price)
    } catch (e: Exception) {
        throw BadRequestException(e.message!!)
    }

    val quantity: Quantity = try {
        Quantity(quantity)
    } catch (e: Exception) {
        throw BadRequestException(e.message!!)
    }

    val comment: String = comment

}
