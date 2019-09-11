package freitech.se.ec.config

import freitech.se.ec.mo.BaseUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing
class JpaAuditorAwareConfig {

    @Bean
    fun auditorAware(): AuditorAware<BaseUser> {
        return SpringSecurityAuditor()
    }

    class SpringSecurityAuditor: AuditorAware<BaseUser> {
        override fun getCurrentAuditor(): Optional<BaseUser> {
            val context = SecurityContextHolder.getContext()
            val authentication: Authentication? = context.authentication
            authentication?.let {
                return Optional.of(it.principal as BaseUser)
            }
            return Optional.empty()
        }
    }
}

