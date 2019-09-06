package freitech.se.ec.service

import com.stripe.Stripe
import com.stripe.exception.StripeException
import com.stripe.model.Charge
import freitech.se.ec.config.StripeKey
import freitech.se.ec.result.ChargeResult
import freitech.se.ec.service.settlement.ChargeService
import freitech.se.ec.stripe.request.builder.settlement.ChargeRequestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StripeChargeService: ChargeService<Map<String, Any>> {

    @Autowired
    lateinit var stripeKey: StripeKey

    override fun charge(builder: ChargeRequestBuilder<Map<String, Any>>): ChargeResult {
        Stripe.apiKey = stripeKey.secretKey
        return try {
            val result = Charge.create(builder.build())
            ChargeResult.Success(result.amount.toInt())
        } catch (e: StripeException) {
            ChargeResult.Failed(e.message!!)
        }
    }

}
