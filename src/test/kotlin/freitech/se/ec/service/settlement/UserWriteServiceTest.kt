package freitech.se.ec.service.settlement

import freitech.se.ec.enums.UserType
import freitech.se.ec.exception.RepositoryException
import freitech.se.ec.mo.User
import freitech.se.ec.param.SignInParam
import freitech.se.ec.repository.read.UserRepository
import org.hamcrest.Matchers
import org.hamcrest.Matchers.any
import org.hamcrest.Matchers.anything
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaSystemException
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class UserWriteServiceTest {

    @InjectMocks
    private lateinit var userWriteService: UserWriteService

    @Mock
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun case_保存に成功した場合() {
        Mockito.`when`(userRepository.save(this.any(User::class.java))).thenReturn(User())
        userWriteService.save(SignInParam(name = "", email = "", side = UserType.Cutomer.code, password = ""))
    }


    @Test(expected = IllegalArgumentException::class)
    fun case_ユーザタイプが未設定の場合() {
        Mockito.`when`(userRepository.save(this.any(User::class.java))).thenReturn(User())
        userWriteService.save(SignInParam(name = "", email = "", side = UserType.None.code, password = ""))
    }


    @Test(expected = RepositoryException::class)
    fun case_Exceptionが発生した場合() {
        Mockito.`when`(userRepository.save(this.any(User::class.java))).thenThrow(JpaSystemException::class.java)
        userWriteService.save(SignInParam(name = "", email = "", side = UserType.Cutomer.code, password = ""))
    }

    private fun <T> any(clazz: Class<T>): T {
        return Mockito.any(clazz)
    }
}