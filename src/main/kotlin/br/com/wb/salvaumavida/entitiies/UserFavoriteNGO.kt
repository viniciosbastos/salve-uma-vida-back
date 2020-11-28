package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.response.FavoriteResponseDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_favorite_ngo")
data class UserFavoriteNGO (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var favoriteSince: Date,
        @ManyToOne
        var starred: User,
        @ManyToOne
        var starredBy: User
){
}

fun UserFavoriteNGO.mapToFavoriteDTO(): FavoriteResponseDTO {
        return FavoriteResponseDTO(
                name = this.starred.name,
                image = this.starred.image
        )
}