package freitech.se.ec.service

import com.stripe.Stripe
import com.stripe.exception.StripeException
import com.stripe.model.PaymentIntent
import freitech.se.ec.config.StripeKey
import freitech.se.ec.result.PaymentResult
import freitech.se.ec.service.settlement.PaymentService
import freitech.se.ec.stripe.request.builder.settlement.PaymentRequestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StripePaymentService: PaymentService<Map<String, Any>> {

    @Autowired
    lateinit var stripeKey: StripeKey

    override fun payment(builder: PaymentRequestBuilder<Map<String, Any>>): PaymentResult {
        Stripe.apiKey = stripeKey.secretKey
        return try {
            val result = PaymentIntent.create(builder.build())
            PaymentResult.Success(result.amount.toInt())
        } catch (e: StripeException) {
            PaymentResult.Failed(e.message!!)
        }
    }

}
