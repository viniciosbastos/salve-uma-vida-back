package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.UserDTO
import br.com.wb.salvaumavida.dto.mapToEntity
import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun find(id: Int): User {
        return userRepository
                .findById(id)
                .orElseThrow { NotFoundException("Usuário não foi encontrado") }
    }

    fun find(email: String): User {
        return userRepository
                .findByEmail(email)
                .orElseThrow { NotFoundException("Usário não foi encontrado") }
    }

    fun saveUser(userDto: UserDTO): User {
        val user = userDto.mapToEntity()
        user.password = passwordEncoder.encode(userDto.password)
        return userRepository.save(user)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }
}