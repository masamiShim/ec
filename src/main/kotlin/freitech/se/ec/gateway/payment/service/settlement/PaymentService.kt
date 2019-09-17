package freitech.se.ec.service.settlement

import freitech.se.ec.result.PaymentResult
import freitech.se.ec.stripe.request.builder.settlement.PaymentRequestBuilder

interface PaymentService<T> {
    fun payment(builder: PaymentRequestBuilder<T>): PaymentResult
}
