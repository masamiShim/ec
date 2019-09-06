package freitech.se.ec.service.settlement

import freitech.se.ec.result.ChargeResult
import freitech.se.ec.stripe.request.builder.settlement.ChargeRequestBuilder

interface ChargeService<T> {
    fun charge(builder: ChargeRequestBuilder<T>): ChargeResult
}
