package freitech.se.ec.framework.filter

import com.fasterxml.jackson.databind.ObjectMapper
import freitech.se.ec.config.SecurityTokenConfig
import freitech.se.ec.gateway.db.mo.User
import freitech.se.ec.param.LoginParam
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager, bCryptPasswordEncoder: BCryptPasswordEncoder, private val securityTokenConfig: SecurityTokenConfig) : UsernamePasswordAuthenticationFilter() {
    var authManager: AuthenticationManager = authenticationManager
    var bCryptPassEncoder: BCryptPasswordEncoder = bCryptPasswordEncoder

    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/authorize", "POST"))
        usernameParameter = "username"
        passwordParameter = "password"
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginParam = try {
            ObjectMapper().readValue(request?.inputStream, LoginParam::class.java)
        } catch (e: IOException) {
            throw RuntimeException(e.message ?: "error has occurred at attempt authentication")
        }
        return authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginParam.username,
                        loginParam.password,
                        mutableListOf())
        )
    }


    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val expirationTime = securityTokenConfig.expirationTime.toInt()
        val token = Jwts.builder()
                .setSubject((authResult?.principal as User).email)
                .setExpiration(Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, securityTokenConfig.secret.toByteArray())
                .compact()
        response?.addHeader(securityTokenConfig.headerName, securityTokenConfig.prefix + token)
    }
}
