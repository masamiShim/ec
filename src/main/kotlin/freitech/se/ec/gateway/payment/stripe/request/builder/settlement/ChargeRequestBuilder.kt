package freitech.se.ec.stripe.request.builder.settlement

interface ChargeRequestBuilder<T> {
    fun build(): T
}
