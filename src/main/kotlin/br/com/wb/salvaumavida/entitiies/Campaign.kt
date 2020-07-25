package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.dto.CampaignItemDTO
import java.util.Date
import javax.persistence.*

@Entity
data class Campaign (
        @Id
        @GeneratedValue
        var id: Int?,
        var title: String,
        @OneToMany(mappedBy = "campaign", cascade = [CascadeType.ALL])
        var items: List<CampaignItem>,
        var limitDate: Date,
        @ManyToOne
        var user: User
){

}

fun Campaign.mapToDTO(): CampaignDTO = CampaignDTO(
        id = this.id,
        title = this.title,
        limitDate = this.limitDate,
        items = this.items.map { it.mapToDTO() }
)