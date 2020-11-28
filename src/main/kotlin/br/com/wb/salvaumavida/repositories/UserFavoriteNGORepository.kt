package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.UserFavoriteNGO
import org.springframework.data.jpa.repository.JpaRepository

interface UserFavoriteNGORepository : JpaRepository<UserFavoriteNGO, Int> {
}