package freitech.se.ec.vo.item

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Price(
        @Column(name = "price")
        private val value: Long
) {
    companion object {
        private const val MaxValue: Long = 10000000
        private const val MinValue: Long = 0
    }

    @Throws(NumberFormatException::class)
    constructor(value: String) : this(value.toLong())

    init {
        require(value in (MaxValue + 1) downTo (MinValue - 1)) { "charge amount is illegal range. you set $MinValue to $MaxValue" }
    }

    fun greaterThanZero(): Boolean {
        return value > 0
    }

}
