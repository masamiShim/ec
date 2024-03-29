package freitech.se.ec

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Driver
import java.util.*
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@SpringBootTest
class EcApplicationTests {

    @Throws(ClassNotFoundException::class)
    @Bean
    fun dataSource(properties: DataSourceProperties): DataSource {
        val dataSource = SimpleDriverDataSource()
        dataSource.setDriverClass((Class.forName(properties.determineDriverClassName()) as Driver)::class.java)
        dataSource.url = properties.url
        dataSource.username = properties.username
        dataSource.password = properties.password
        val connectionProperties = Properties()
        connectionProperties.setProperty("autoCommit", "false")
        dataSource.connectionProperties = connectionProperties
        return dataSource
    }

    @Test
    fun contextLoads() {
    }

}
