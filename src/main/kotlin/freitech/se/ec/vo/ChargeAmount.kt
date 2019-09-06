package freitech.se.ec.vo


class ChargeAmount(val value: String) {
    companion object {
        const val MaxValue: Long = 10000000
        const val MinValue: Long = 1
    }

    constructor(value: Long) : this(value.toString()) {
    }

    init {
        val longValue: Long = value.toLong()
        require(longValue in (MaxValue + 1)..(MinValue - 1)) { "charge amount is illegal range. you set ${MinValue} to ${MaxValue}" }
    }

    fun toLong(): Long {
        return this.value.toLong()
    }
}
