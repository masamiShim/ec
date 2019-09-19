package freitech.se.ec.domain.service.write

import freitech.se.ec.exception.ExistsEmailException
import freitech.se.ec.exception.RepositoryException
import freitech.se.ec.gateway.db.mo.Customer
import freitech.se.ec.gateway.db.repository.read.UserRepository
import freitech.se.ec.param.SignInParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserWriteService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Throws(ExistsEmailException::class, RepositoryException::class)
    fun save(signInParam: SignInParam) {
        try {
            val user = signInParam.toUser(bCryptPasswordEncoder)
            if(userRepository.findByEmail(user.email).isPresent){
                // FIXME: これはメッセージとして出さないほうがよさそう
                throw ExistsEmailException("mail address already registered")
            }
            userRepository.save(signInParam.side.createUser(user))
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
