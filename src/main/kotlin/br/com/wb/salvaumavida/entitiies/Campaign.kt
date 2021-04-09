package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.dto.CampaignItemDTO
import java.util.Date
import javax.persistence.*

@Entity
data class Campaign (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var title: String,
        @OneToMany(mappedBy = "campaign", cascade = [CascadeType.ALL])
        var items: List<CampaignItem>,
        var limitDate: Date,
        var description: String,
        @ManyToOne
        var user: User,
        var open: Boolean = true
){
}

fun Campaign.mapToDTO(): CampaignDTO = CampaignDTO(
        id = this.id,
        title = this.title,
        limitDate = this.limitDate,
        items = this.items.map { it.mapToDTO() },
        description = this.description,
        userImage = this.user.image,
        userId = this.user.id,
        open = this.open
)

fun Campaign.canBeClosed(): Boolean {
        return items
                .none { it.progress > 0 }
}

