package freitech.se.ec.mo

data class ItemImage(
        val itemId: Id,
        val image: String,
        val isPrimary: Boolean) : SecurityAudit() {
}
