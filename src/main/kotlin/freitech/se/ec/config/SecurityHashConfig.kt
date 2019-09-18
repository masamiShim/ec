package freitech.se.ec.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application.security.hash")
class SecurityHashConfig() {
    var algorithm = ""
    var seed = ""
    var stretch = 0
}
