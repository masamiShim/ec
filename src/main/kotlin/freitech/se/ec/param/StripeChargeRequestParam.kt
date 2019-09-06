package freitech.se.ec.param

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.vo.ChargeAmount
import freitech.se.ec.vo.Email

/**
 * BadRequestException
 */
class StripeChargeRequestParam(amount: String, mail: String) {
    val amount: ChargeAmount = try {
        ChargeAmount(amount)
    } catch (e: Exception) {
        throw BadRequestException(e.message!!)
    }
    val mail: Email = try {
        Email(mail)
    } catch (e: Exception) {
        throw BadRequestException(e.message!!)
    }
}
