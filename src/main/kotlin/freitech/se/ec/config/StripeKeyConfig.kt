package freitech.se.ec.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:stripekey.properties")
class StripeKeyConfig {
    @Value("\${application.stripe.key.api}")
    lateinit var apiKey: String

    @Value("\${application.stripe.key.secret}")
    lateinit var secretKey: String

    @Bean
    fun stripeKey(): StripeKey {
        return StripeKey(apiKey, secretKey)
    }
}
