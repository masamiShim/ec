package freitech.se.ec.mo

import freitech.se.ec.config.NoArg
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@NoArg
@Entity
class User(
        @Column(name = "email", nullable = false)
        val email: String = "",
        @Column(name = "password", nullable = false)
        val pass: String = "",
        @Column(name = "enable", nullable = false)
        val enable: Boolean = false,
        @Column(name = "expired", nullable = false)
        val expired: Boolean = false,
        @Column(name = "credential", nullable = false)
        val credential: Boolean = false,
        @Column(name = "failure", nullable = false)
        val failure: Short = 0,
        @Column(name = "locked", nullable = false)
        val locked: Boolean = false,

        @OneToOne(cascade = [CascadeType.ALL], optional = true)
        @JoinTable(name = "rel_user_exhibitor",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "exhibitor_id", referencedColumnName = "id")])
        val exhibitor: Exhibitor? = null,

        @OneToOne(cascade = [CascadeType.ALL], optional = true)
        @JoinTable(name = "rel_user_customer",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "customer_id", referencedColumnName = "id")])
        val customer: Customer? = null
) : UserDetails, SecurityAudit() {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun isEnabled(): Boolean {
        return enable
    }

    override fun getUsername(): String {
        return email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credential;
    }

    override fun getPassword(): String {
        return pass
    }

    override fun isAccountNonExpired(): Boolean {
        return expired
    }

    override fun isAccountNonLocked(): Boolean {
        return locked
    }
}
