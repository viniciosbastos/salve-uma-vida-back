package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.CampaignItemDTO
import javax.persistence.*

@Entity
data class CampaignItem (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var description: String,
        var goal: Float,
        var progress: Float,
        @ManyToOne
        var campaign: Campaign,
        @Enumerated(EnumType.STRING)
        var unit: UnitType
){
}

fun CampaignItem.mapToDTO(): CampaignItemDTO = CampaignItemDTO(
        id = this.id,
        description = this.description,
        goal = this.goal,
        progress = this.progress,
        unit = this.unit
)
