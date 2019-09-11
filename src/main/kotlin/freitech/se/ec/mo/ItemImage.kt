package freitech.se.ec.mo

data class ItemImage(
        override val id: Int = 0,
        val itemId: Id,
        val image: String,
        val isPrimary: Boolean) : SecurityAudit() {
}
