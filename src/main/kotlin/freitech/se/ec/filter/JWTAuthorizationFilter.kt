package freitech.se.ec.filter

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager?) : BasicAuthenticationFilter(authenticationManager) {

    @Value("\${application.security.token.headerName:X-TOKEN-EC}")
    val HEADER_NAME: String = ""

    @Value("\${application.security.token.prefix:tk_ec_app}")
    val PREFIX: String = ""

    @Value("\${application.security.token.secret:fapw3fei\$samale}")
    val SECRET: String =""


    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        // ヘッダの存在とPrefixをチェック
        val header = request.getHeader(HEADER_NAME)
        if (header == null || !header.startsWith(PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        // 認証情報を取得
        val authentication: UsernamePasswordAuthenticationToken? = getAuthentication(request)

        // コンテキストに設定
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token: String? = request.getHeader(HEADER_NAME)
        token?.let {
            val user: String? = Jwts.parser()
                    .setSigningKey(SECRET.toByteArray())
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .body
                    .subject

            user?.let {
                return UsernamePasswordAuthenticationToken(user, null, mutableListOf())
            }
            return null
        }
        return null
    }
}
