package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.models.User
import br.com.wb.salvaumavida.repositories.UserRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService (
        private val userRepository: UserRepository
) {

    fun find(id: Int): User = userRepository.findById(id).orElseThrow { RuntimeException("Nao encontrado") }

    fun saveUser(user: User) {
        userRepository.save(user)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }
}