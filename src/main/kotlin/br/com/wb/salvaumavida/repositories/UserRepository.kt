package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

}