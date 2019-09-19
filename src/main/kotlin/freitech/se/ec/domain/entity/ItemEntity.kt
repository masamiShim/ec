package freitech.se.ec.domain.entity

import freitech.se.ec.config.NoArg
import freitech.se.ec.gateway.db.mo.Id
import freitech.se.ec.vo.item.Price
import freitech.se.ec.vo.item.Quantity
import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

@NoArg
@Embeddable
data class ItemEntity(
        @Embedded
        @AttributeOverride(name = "value", column = Column(name = "exhibitor_id"))
        val exhibitorId: Id = Id(0),
        val name: String = "",
        val code: String = "",
        @Embedded
        val price: Price = Price(0),
        @Embedded
        val quantity: Quantity = Quantity(0),
        val comment: String = ""
)
