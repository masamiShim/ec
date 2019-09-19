package freitech.se.ec.domain.service.read

import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.gateway.db.mo.User
import freitech.se.ec.gateway.db.repository.read.UserRepository
import freitech.se.ec.vo.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserReadService {

    @Autowired
    lateinit var userRepository: UserRepository

    /**
     * Emailアドレスからユーザを取得する
     */
    fun findUserByEmail(email: Email): User {
        return userRepository.findByEmail(email.value).orElseThrow { throw NotFoundException("user not found") }
    }
}
