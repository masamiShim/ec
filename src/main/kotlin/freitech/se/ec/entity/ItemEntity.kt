package freitech.se.ec.entity

data class ItemEntity(
        val exhibitorId: Int? = null,
        val name: String = "",
        val code: String = "",
        val price: Int = 0,
        val quantity: Int = 0
)
