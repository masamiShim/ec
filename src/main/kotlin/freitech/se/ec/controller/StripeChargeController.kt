package freitech.se.ec.controller

import com.stripe.Stripe
import com.stripe.exception.StripeException
import com.stripe.model.Charge
import freitech.se.ec.config.StripeKey
import freitech.se.ec.response.BaseResponse
import freitech.se.ec.stripe.request.builder.StripeChargeParamBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StripeChargeController {
    @Autowired
    lateinit var stripeKey: StripeKey


    @PostMapping("/stripe/charge")
    fun stripeCharge(@RequestParam("amount") amount: String, @RequestParam("mail") mail: String): ResponseEntity<BaseResponse> {
        val chargeResult = charge(amount.toInt(), mail)
        return when (chargeResult) {
            is ChargeResult.Failed -> ResponseEntity.ok(BaseResponse("faild"))
            is ChargeResult.Success -> ResponseEntity.ok(BaseResponse(chargeResult.amount.toString()))
        }
    }

    private fun charge(amount: Int, mail: String): ChargeResult {
        Stripe.apiKey = stripeKey.secretKey
        val builder = StripeChargeParamBuilder(amount, mail)
        try {
            val result = Charge.create(builder.build())
            return ChargeResult.Success(result.amount.toInt())
        } catch (e: StripeException) {
            return ChargeResult.Failed
        }
        val chargeResult = Charge.create(builder.build())

    }

    sealed class ChargeResult {
        data class Success(val amount: Int) : ChargeResult()
        object Failed : ChargeResult()
    }
}
