package freitech.se.ec.domain.service

import freitech.se.ec.config.SecurityHashConfig
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
class EcHashServiceTest {

    @InjectMocks
    lateinit var ecHashService: EcHashService

    @Mock
    lateinit var ecSecurityHashConfig: SecurityHashConfig

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(ecSecurityHashConfig.algorithm).thenReturn("PBKDF2WithHmacSHA256")
        Mockito.`when`(ecSecurityHashConfig.seed).thenReturn("hogehoge")
        Mockito.`when`(ecSecurityHashConfig.stretch).thenReturn(12)
    }

    @Test
    fun hashed() {
        val input = "password"
        val hashed1 = ecHashService.hashed(input)
        val hashed2 = ecHashService.hashed(input)
        println(hashed1)
        println(hashed2)

        assertThat(hashed1, Matchers.`is`(hashed2))
    }

    @Test
    fun match() {
    }
}
