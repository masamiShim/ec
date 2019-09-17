package freitech.se.ec.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.stereotype.Component
import java.sql.Driver
import java.util.*
import javax.sql.DataSource

@Component
@Configuration
@Profile("test")
class DataSourceConfiguration {

    @Throws(ClassNotFoundException::class)
    @Bean(name = ["dataSource"])
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
}
