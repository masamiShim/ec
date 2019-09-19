package freitech.se.ec.domain.service.write

import freitech.se.ec.exception.RepositoryException
import freitech.se.ec.gateway.db.repository.read.UserRepository
import freitech.se.ec.param.SignInParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserWriteService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun save(signInParam: SignInParam) {
        try {
            var user = signInParam.toUser(bCryptPasswordEncoder)
            val persistedUser = userRepository.save(user)
            userRepository.save(signInParam.side.createUser(persistedUser))
        } catch (e: Exception) {
            when (e) {
                is IllegalArgumentException -> throw e
                else -> {
                    e.printStackTrace()
                    throw RepositoryException("user register has error occurred")
                }
            }
        }
    }
}
