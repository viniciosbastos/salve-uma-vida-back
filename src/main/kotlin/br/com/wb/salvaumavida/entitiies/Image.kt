package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.ImageDTO
import javax.persistence.*

@Entity
data class Image (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var image: String,
        @ManyToOne
        var user: User
        ){}

fun Image.mapToDTO(): ImageDTO {
        return ImageDTO(
                id = this.id,
                image = this.image
        )
}