package freitech.se.ec.gateway.db.mo

data class ItemImage(
        val itemId: Id,
        val image: String,
        val isPrimary: Boolean) : SecurityAudit() {
}
