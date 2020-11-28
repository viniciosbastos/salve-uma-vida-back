package br.com.wb.salvaumavida.entitiies

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_favorite_ngo")
data class UserFavoriteNGO (

        @Id
        var id: Int? = null,
        var favoriteSince: Date,
        @ManyToOne
        var starred: User,
        @ManyToOne
        var starredBy: User
){


}