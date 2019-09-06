package freitech.se.ec.controller.settlement

import freitech.se.ec.response.BaseResponse
import freitech.se.ec.result.PaymentResult
import freitech.se.ec.service.StripePaymentService
import freitech.se.ec.stripe.request.builder.StripePaymentParamBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StripePayementController {

    @Autowired
    lateinit var stripePaymentService: StripePaymentService

    @GetMapping("/stripe/payment")
    fun testPayment(): ResponseEntity<BaseResponse> {
        val builder = StripePaymentParamBuilder(99, "mail@test.com", "card")
        val paymentResult = stripePaymentService.payment(builder)
        return when (paymentResult) {
            is PaymentResult.Failed -> ResponseEntity.ok(BaseResponse("faild"))
            is PaymentResult.Success -> ResponseEntity.ok(BaseResponse(paymentResult.amount.toString()))
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

}
