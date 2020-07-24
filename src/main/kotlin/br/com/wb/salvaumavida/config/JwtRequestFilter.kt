package br.com.wb.salvaumavida.config

import br.com.wb.salvaumavida.services.JwtUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter (
        private val userDetailsService: JwtUserDetailsService,
        private val tokenUtil: JwtTokenUtil
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val requestTokenHeader = request.getHeader("Authorization")
        var username = ""
        var jwtToken = ""

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
            try {
                username = tokenUtil.getUsernameFromToken(jwtToken)
            } catch (e: IllegalArgumentException) {
                println("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                println("JWT Token has expired")
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String")
        }
        if (username.isNotEmpty() && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(username)
            if (tokenUtil.validateToken(jwtToken, userDetails)) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request, response)
    }
}