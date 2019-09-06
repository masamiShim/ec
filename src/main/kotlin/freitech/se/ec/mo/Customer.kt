package freitech.se.ec.mo

data class Customer(
        val id: Id,
        val name: String,
        val email: String,
        val password: String,
        val verified: Boolean,
        val audit: SecurityAudit) {
}
