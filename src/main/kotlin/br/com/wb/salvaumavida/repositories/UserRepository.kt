package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): Optional<User>
}