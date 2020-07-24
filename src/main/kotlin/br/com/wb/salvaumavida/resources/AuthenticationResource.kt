package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.config.JwtTokenUtil
import br.com.wb.salvaumavida.dto.AuthenticationDTO
import br.com.wb.salvaumavida.dto.JwtDTO
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.JwtUserDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationResource (
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenUtil: JwtTokenUtil,
        private val userDetailsService: JwtUserDetailsService
){

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authentication: AuthenticationDTO): Response {
        return try {
            authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(
                            authentication.username,
                            authentication.password)
                    )
            val userDetails = userDetailsService.loadUserByUsername(authentication.username)
            Response.Success(JwtDTO(jwtTokenUtil.generateToken(userDetails)))
        } catch (ex: BadCredentialsException) {
            Response.Error("Nenhum usuário encontrado com as credenciais informadas.", ex.cause.toString())
        } catch (ex: DisabledException) {
            Response.Error("Conta desabilitada", ex.cause.toString())
        }
    }
}