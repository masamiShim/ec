package freitech.se.ec.domain.service.write

import freitech.se.ec.exception.RepositoryException
import freitech.se.ec.param.SignInParam
import freitech.se.ec.repository.read.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserWriteService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun save(signInParam: SignInParam) {
        try {
            userRepository.save(signInParam.toUser())
        } catch (e: Exception) {
            when(e) {
                is IllegalArgumentException -> throw e
                else -> throw RepositoryException("user register has error occurred")
            }
        }
    }
}
