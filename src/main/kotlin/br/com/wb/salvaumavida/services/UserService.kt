package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository
) {

    fun find(id: Int): User {
        return userRepository
                .findById(id)
                .orElseThrow { NotFoundException("Usuário não foi encontrado") }
    }

    fun saveUser(user: User) {
        userRepository.save(user)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }
}