package freitech.se.ec.result

sealed class ChargeResult {
    data class Success(val amount: Int) : ChargeResult()
    data class Failed(val message: String) : ChargeResult()
}

sealed class PaymentResult {
    data class Success(val amount: Int) : PaymentResult()
    data class Failed(val message: String) : PaymentResult()
}
