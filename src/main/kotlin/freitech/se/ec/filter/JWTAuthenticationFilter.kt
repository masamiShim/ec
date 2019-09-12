package freitech.se.ec.filter

import com.fasterxml.jackson.databind.ObjectMapper
import freitech.se.ec.mo.User
import freitech.se.ec.param.LoginParam
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
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

class JWTAuthenticationFilter : UsernamePasswordAuthenticationFilter {

    @Value("\${application.security.token.expiration}")
    lateinit var Expiration_Time: String

    @Value("\${application.security.token.secret}")
    lateinit var SECRET: String

    @Value("\${application.security.token.headerName}")
    lateinit var HEADER_NAME: String

    @Value("\${application.security.token.prefix}")
    lateinit var PREFIX: String

    private val authManager: AuthenticationManager
    private val bCryptPasswordEncoder: BCryptPasswordEncoder

    constructor(authManager: AuthenticationManager, bCryptPasswordEncoder: BCryptPasswordEncoder) : super() {
        this.authManager = authManager
        this.bCryptPasswordEncoder = bCryptPasswordEncoder

        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/login", "POST"))

        usernameParameter = "username"
        passwordParameter = "password"
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginParam = try {
            ObjectMapper().readValue(request?.inputStream, LoginParam::class.java)
        } catch (e: IOException) {
            throw RuntimeException(e.message ?: "error has occurred at attempt authentication")
        }
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginParam.username,
                        loginParam.password,
                        mutableListOf())
        )
    }


    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val expirationTime = Expiration_Time.toInt()
        val token = Jwts.builder()
                .setSubject((authResult?.principal as User).email)
                .setExpiration(Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET.toByteArray())
                .compact()
        response?.addHeader(HEADER_NAME, PREFIX + token)
    }
}
