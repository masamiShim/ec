package freitech.se.ec.mo

data class ItemImage(
        val id: Id,
        val itemId: Id,
        val image: String,
        val isPrimary: Boolean,
        val audit: SecurityAudit) {
}
