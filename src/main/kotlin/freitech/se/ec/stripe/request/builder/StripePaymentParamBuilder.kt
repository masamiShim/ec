package freitech.se.ec.stripe.request.builder

class StripePaymentParamBuilder(private val amount: Int = 0, private val receiptEmail: String, private val method: String) {
    var currency: String = "jpy"

    fun build(): Map<String, Any> {
        return mapOf(
                Pair("amount", amount),
                Pair("currency", currency),
                Pair("payment_method_types", listOf(method)),
                Pair("receipt_email", receiptEmail))
    }
}
