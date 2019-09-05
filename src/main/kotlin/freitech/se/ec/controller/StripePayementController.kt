package freitech.se.ec.controller

import com.stripe.Stripe
import com.stripe.exception.StripeException
import com.stripe.model.Charge
import com.stripe.model.PaymentIntent
import freitech.se.ec.config.StripeKey
import freitech.se.ec.response.BaseResponse
import freitech.se.ec.stripe.request.builder.StripePaymentParamBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StripePayementController {
    @Autowired
    lateinit var stripeKey: StripeKey

    @GetMapping("/stripe/payment")
    fun testPayment(): ResponseEntity<BaseResponse> {
        val chargeResult = payment(99, "mail@test.com", "card")
        return when (chargeResult) {
            is ChargeResult.Failed -> ResponseEntity.ok(BaseResponse("faild"))
            is ChargeResult.Success -> ResponseEntity.ok(BaseResponse(chargeResult.amount.toString()))
        }
    }

/*
    @PostMapping("/stripe/payment")
    fun stripeCharge(@RequestParam("amount") amount: String, @RequestParam("mail") mail: String): ResponseEntity<BaseResponse> {
        val chargeResult = charge(amount.toInt(), mail)
        return when (chargeResult) {
            is ChargeResult.Failed -> ResponseEntity.ok(BaseResponse("faild"))
            is ChargeResult.Success -> ResponseEntity.ok(BaseResponse(chargeResult.amount.toString()))
        }
    }
*/
    private fun payment(amount: Int, mail: String, method: String): ChargeResult {
        Stripe.apiKey = stripeKey.secretKey
        val builder = StripePaymentParamBuilder(amount, mail, method)
        try {
            val result = PaymentIntent.create(builder.build())
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
