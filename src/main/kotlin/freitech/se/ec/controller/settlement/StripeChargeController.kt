package freitech.se.ec.controller.settlement

import freitech.se.ec.param.StripeChargeRequestParam
import freitech.se.ec.response.BaseResponse
import freitech.se.ec.result.ChargeResult
import freitech.se.ec.service.StripeChargeService
import freitech.se.ec.stripe.request.builder.StripeChargeParamBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StripeChargeController {

    @Autowired
    lateinit var stripeChargeService: StripeChargeService

    @PostMapping("/stripe/charge")
    fun stripeCharge(@RequestParam("amount") amount: String, @RequestParam("mail") mail: String): ResponseEntity<BaseResponse> {

        val requestParam = StripeChargeRequestParam(amount, mail)

        val builder = StripeChargeParamBuilder(requestParam.amount.toLong(), requestParam.mail.value)

        val chargeResult = stripeChargeService.charge(builder)
        return when (chargeResult) {
            is ChargeResult.Failed -> ResponseEntity.ok(BaseResponse("faild"))
            is ChargeResult.Success -> ResponseEntity.ok(BaseResponse(chargeResult.amount.toString()))
        }
    }
}
