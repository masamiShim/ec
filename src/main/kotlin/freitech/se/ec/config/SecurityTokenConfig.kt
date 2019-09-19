package freitech.se.ec.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application.security.token")
class SecurityTokenConfig() {
    lateinit var headerName: String
    lateinit var prefix: String
    lateinit var secret: String
    lateinit var expirationTime: String
    lateinit var allowDomain: String
}
