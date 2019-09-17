package freitech.se.ec.stripe.request.builder

import freitech.se.ec.stripe.request.builder.settlement.PaymentRequestBuilder

class StripePaymentParamBuilder(private val amount: Int = 0, private val receiptEmail: String, private val method: String) : PaymentRequestBuilder<Map<String, Any>> {
    var currency: String = "jpy"

    override fun build(): Map<String, Any> {
        return mapOf(
                Pair("amount", amount),
                Pair("currency", currency),
                Pair("payment_method_types", listOf(method)),
                Pair("receipt_email", receiptEmail))
    }
}
