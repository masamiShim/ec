package freitech.se.ec.stripe.request.builder

import freitech.se.ec.stripe.request.builder.settlement.ChargeRequestBuilder

class StripeChargeParamBuilder(private val amount: Int = 0, private val receiptEmail: String) : ChargeRequestBuilder<Map<String, Any>> {
    var currency: String = "jpy"
    var source: String = "tok_visa"

    override fun build(): Map<String, Any> {
        //@formatter: off
        return mapOf(
                Pair("amount", amount),
                Pair("currency", currency),
                Pair("source", source),
                Pair("receipt_email", receiptEmail)
        )
        //@formatter: on
    }
}
