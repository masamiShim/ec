package freitech.se.ec.entity

import freitech.se.ec.config.NoArg
import freitech.se.ec.vo.item.Price
import freitech.se.ec.vo.item.Quantity
import javax.persistence.Embeddable

@NoArg
@Embeddable
data class ItemEntity(
        val exhibitorId: Int = 0,
        val name: String = "",
        val code: String = "",
        val price: Price = Price(0),
        val quantity: Quantity = Quantity(0),
        val comment: String = ""
)
