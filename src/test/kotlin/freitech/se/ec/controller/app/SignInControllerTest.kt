package freitech.se.ec.controller.app

import com.fasterxml.jackson.databind.ObjectMapper
import freitech.se.ec.config.AppConfig
import freitech.se.ec.param.SignInParam
import freitech.se.ec.response.BaseResponse
import freitech.se.ec.service.settlement.UserWriteService
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.any
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.RequestMatcher
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [SignInController::class])
@MockBean(JpaMetamodelMappingContext::class)
@ComponentScan(basePackages = ["freitech.se.ec.config"])
class SignInControllerTest {

    @Autowired
    @InjectMocks
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Mock
    lateinit var userWriteService: UserWriteService

    private val contentType = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype, Charset.forName("UTF-8"))

    @Test
    fun signIn() {

        val input = MockJson("hogehoge", "hogehoge@hoge.com", "hogepassword", "1")
        Mockito.doNothing().`when`(userWriteService.save(input.toSignInParam()))

        val inputJson = objectMapper.writeValueAsString(input)

        val expected = BaseResponse(message = "success")
        val expectedJson = objectMapper.writeValueAsString(expected)

        val builder = MockMvcRequestBuilders.post("/signIn")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(inputJson)

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson))
    }

    data class MockJson(val name: String,val email: String,val password: String,val side: String) {

        fun toSignInParam(): SignInParam {
            return SignInParam(name, email, password, side)
        }

    }
}