package freitech.se.ec.config

import freitech.se.ec.mo.User
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
    fun auditorAware(): AuditorAware<Long> {
        return SpringSecurityAuditor()
    }

    class SpringSecurityAuditor : AuditorAware<Long> {
        override fun getCurrentAuditor(): Optional<Long> {
            val authentication: Authentication? = SecurityContextHolder.getContext().authentication

            if (authentication == null || !authentication.isAuthenticated) {
                return Optional.empty()
            }
            return Optional.of((authentication.principal as User).id.value)
        }
    }
}

