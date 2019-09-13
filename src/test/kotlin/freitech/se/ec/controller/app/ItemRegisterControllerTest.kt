package freitech.se.ec.controller.app

import com.fasterxml.jackson.databind.ObjectMapper
import freitech.se.ec.config.AppConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@WebMvcTest(
        controllers = [ItemRegisterController::class]
        , excludeAutoConfiguration = [
    DataSourceAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    JpaRepositoriesAutoConfiguration::class,
    JpaBaseConfiguration::class,
    JndiConnectionFactoryAutoConfiguration::class
])
@MockBean(JpaMetamodelMappingContext::class)
@ComponentScan(basePackages = ["freitech.se.ec.config"])
class ItemRegisterControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var appConfig: AppConfig

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val contentType = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype, Charset.forName("UTF-8"))

    /**
     * 疎通確認
     */
    @Test
    fun isHealthy() {
        /*
        val expected = BaseResponse(message = "isHealthy")
        val expectedJson = objectMapper.writeValueAsString(expected)
        */
        val builder = MockMvcRequestBuilders.post("/owner/item/register")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header(appConfig.headerName, appConfig.prefix + "aG9nZWhvZ2Vob2dlOmhvZ2Vob2dlaG9lZw==")
                .with(SecurityMockMvcRequestPostProcessors.csrf())

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
