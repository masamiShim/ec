package freitech.se.ec.config

import freitech.se.ec.filter.JWTAuthenticationFilter
import freitech.se.ec.filter.JWTAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
    lateinit var appConfig: AppConfig

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private lateinit var userDetailsService: UserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        // @formatter:off
        http?.
                cors()?.
                and()?.
                authorizeRequests()?.antMatchers("/login", "/signup", "/logout", "/authorize" )?.
                permitAll()?.
                anyRequest()?.authenticated()?.
                and()?.logout()?.
                and()?.csrf()?.disable()?.
                addFilter(JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder(), appConfig))?.
                addFilter(JWTAuthorizationFilter(authenticationManager(), appConfig))?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
        corsConfiguration.addExposedHeader(appConfig.headerName)
        corsConfiguration.addAllowedOrigin(appConfig.allowDomain)
        corsConfiguration.allowCredentials = true

        val corsSource = UrlBasedCorsConfigurationSource()
        corsSource.registerCorsConfiguration("/**", corsConfiguration)
        return corsSource
    }

}
