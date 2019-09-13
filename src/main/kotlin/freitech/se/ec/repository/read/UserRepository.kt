package freitech.se.ec.repository.read

import freitech.se.ec.mo.Id
import freitech.se.ec.mo.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Id> {
    fun findByEmail(email: String): Optional<User>
}
