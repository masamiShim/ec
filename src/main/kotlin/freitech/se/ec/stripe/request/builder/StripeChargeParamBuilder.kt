package freitech.se.ec.stripe.request.builder

class StripeChargeParamBuilder(private val amount: Int = 0, private val receiptEmail: String) {
    var currency: String = "jpy"
    var source: String = "tok_visa"

    fun build(): Map<String, Any> {
        return mapOf(Pair("amount", amount), Pair("currency", currency), Pair("source", source), Pair("receipt_email", receiptEmail))
    }
}
