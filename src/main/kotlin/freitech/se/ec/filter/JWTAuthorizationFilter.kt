package freitech.se.ec.filter

import freitech.se.ec.config.AppConfig
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager?, val appConfig: AppConfig) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        // ヘッダの存在とPrefixをチェック
        val header = request.getHeader(appConfig.headerName)
        if (header == null || !header.startsWith(appConfig.prefix)) {
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
        val token: String? = request.getHeader(appConfig.headerName)
        token?.let {
            val user: String? = Jwts.parser()
                    .setSigningKey(appConfig.secret.toByteArray())
                    .parseClaimsJws(token.replace(appConfig.prefix, ""))
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
