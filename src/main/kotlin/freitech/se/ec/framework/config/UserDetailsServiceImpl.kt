package freitech.se.ec.config

import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.repository.read.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service("UserDetailsServiceImpl")
class UserDetailsServiceImpl : UserDetailsService {


    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username ?: "").orElseThrow { throw NotFoundException("user not found") }
    }
}
