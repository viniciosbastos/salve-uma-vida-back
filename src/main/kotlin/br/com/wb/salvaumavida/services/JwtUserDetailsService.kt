package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class JwtUserDetailsService (
        private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository
                .findByEmail(username)
                .orElseThrow{ UsernameNotFoundException("Usário não foi encontrado") }
        return User(user.email, user.password, mutableListOf())
    }
}