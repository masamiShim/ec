package freitech.se.ec.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.yml")
@Profile("test")
class JwtSettingConfig {
    @Value("\${application.security.token.expiration}")
    lateinit var ExpirationTime: String

    @Value("\${application.security.token.secret}")
    lateinit var Secret: String

    @Value("\${application.security.token.headerName}")
    lateinit var HeaderName: String

    @Value("\${application.security.token.prefix}")
    lateinit var Prefix: String

    @Bean("jwtSetting")
    fun jwtSetting(): JwtSetting {
        return JwtSetting(ExpirationTime, Secret, HeaderName, Prefix)
    }
}

