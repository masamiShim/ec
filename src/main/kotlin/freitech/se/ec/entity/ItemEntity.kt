package freitech.se.ec.entity

import freitech.se.ec.config.NoArg

@NoArg
data class ItemEntity(
        val exhibitorId: Int = 0,
        val name: String = "",
        val code: String = "",
        val price: Int = 0,
        val quantity: Int = 0
)
