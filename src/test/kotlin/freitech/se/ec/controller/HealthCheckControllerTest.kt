package freitech.se.ec.controller

import com.fasterxml.jackson.databind.ObjectMapper
import freitech.se.ec.response.BaseResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@WebMvcTest(HealthCheckController::class)
class HealthCheckControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val contentType = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype, Charset.forName("UTF-8"))

    @Test
    fun success_healthy() {
        val expected = BaseResponse(message = "isHealthy")
        val expectedJson = objectMapper.writeValueAsString(expected)

        val builder = MockMvcRequestBuilders.get("/health")
                .accept(MediaType.APPLICATION_JSON_UTF8)

        mvc.perform(builder)
                .andExpect(status().isOk)
                .andExpect(content().contentType(contentType))
                .andExpect(content().json(expectedJson))
    }

}
