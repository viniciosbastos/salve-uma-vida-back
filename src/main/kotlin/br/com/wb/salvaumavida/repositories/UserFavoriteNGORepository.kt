package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.UserFavoriteNGO
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserFavoriteNGORepository : JpaRepository<UserFavoriteNGO, Int> {

    fun findByStarredBy_Id(id: Int): List<UserFavoriteNGO>

    fun findByStarredBy_IdAndStarred_Id(userId:Int, starredById: Int): Optional<UserFavoriteNGO>
}