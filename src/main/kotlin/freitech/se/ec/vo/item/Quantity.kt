package freitech.se.ec.vo.item

class Quantity(val value: Long) {
    companion object {
        const val MaxValue: Long = 10000000
        const val MinValue: Long = 1
    }

    constructor(value: String) : this(value.toLong()) {
    }

    init {
        val longValue: Long = value
        require(longValue in (MaxValue + 1)..(MinValue - 1)) { "charge amount is illegal range. you set ${MinValue} to ${MaxValue}" }
    }
}
