package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.LocationDTO
import br.com.wb.salvaumavida.dto.UserDTO
import br.com.wb.salvaumavida.dto.mapToEntity
import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.entitiies.UserFavoriteNGO
import br.com.wb.salvaumavida.entitiies.mapToLocationDto
import br.com.wb.salvaumavida.exceptions.EmailAlreadyInUseException
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.UserFavoriteNGORepository
import br.com.wb.salvaumavida.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val userFavoriteNGORepository: UserFavoriteNGORepository
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
    private fun verifyIfUserExists(email: String) {
        val user = userRepository.findByEmail(email)
        if (user.isPresent)
            throw EmailAlreadyInUseException("Já existe um usuário com esse endereço de email.")
    }

    fun createUser(user: UserDTO): User {
        verifyIfUserExists(user.email)
        return saveUser(user)
    }

    fun saveUser(userDto: UserDTO): User {
        val user = userDto.mapToEntity()
        user.password = passwordEncoder.encode(userDto.password)
        return userRepository.save(user)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }

    fun saveFavoriteNgo(username: String, ngoId: Int) {
        val user = find(username)
        val ngo = find(ngoId)
        userFavoriteNGORepository.save(UserFavoriteNGO(
                favoriteSince = Date(),
                starred = ngo,
                starredBy = user
        ))
    }

    fun unFavoriteNgo(username: String, ngoId: Int){
        val user = find(username)
        val ngo = find(ngoId)
        val favoriteNGO = userFavoriteNGORepository
                .findByStarredBy_IdAndStarred_Id(user.id!!, ngo.id!!)
                .orElseThrow { NotFoundException("ONG não favoritada pelo usuário.") }
        userFavoriteNGORepository.delete(favoriteNGO)
    }

    fun findUserFavoriteNgos(name: String): List<UserFavoriteNGO> {
        val user = find(name)
        val favoriteNgos = userFavoriteNGORepository.findByStarredBy_Id(user.id!!)
        return favoriteNgos
    }

    fun findNGOLocations(): List<LocationDTO> {
        val ngoLocations = userRepository
                .findAll()
                .map { it.mapToLocationDto() }
        return ngoLocations
    }
}