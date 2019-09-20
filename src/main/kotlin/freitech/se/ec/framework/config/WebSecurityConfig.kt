package freitech.se.ec.framework.config

import freitech.se.ec.config.SecurityTokenConfig
import freitech.se.ec.framework.filter.JWTAuthenticationFilter
import freitech.se.ec.framework.filter.JWTAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var securityTokenConfig: SecurityTokenConfig

    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private lateinit var userDetailsService: UserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        // @formatter:off
        http?.
                cors()?.
                and()?.
                authorizeRequests()?.antMatchers("/login", "/signin", "/logout", "/authorize" )?.
                permitAll()?.
                anyRequest()?.authenticated()?.
                and()?.logout()?.
                and()?.csrf()?.disable()?.
                addFilter(JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder(), securityTokenConfig, stringRedisTemplate))?.
                addFilter(JWTAuthorizationFilter(authenticationManager(), securityTokenConfig))?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // @formatter:on
    }

    @Autowired
    @Throws(Exception::class)
    fun configureAuth(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)

        // jwt用のヘッダ
        corsConfiguration.addExposedHeader(securityTokenConfig.headerName)
        corsConfiguration.addAllowedOrigin(securityTokenConfig.allowDomain)
        corsConfiguration.allowCredentials = true

        val corsSource = UrlBasedCorsConfigurationSource()
        corsSource.registerCorsConfiguration("/**", corsConfiguration)
        return corsSource
    }

}
