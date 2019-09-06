package freitech.se.ec.stripe.request.builder.settlement

interface PaymentRequestBuilder<T> {
    fun build(): T
}
