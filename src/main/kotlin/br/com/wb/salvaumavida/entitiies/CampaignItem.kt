package br.com.wb.salvaumavida.entitiies

import javax.persistence.*

@Entity
data class CampaignItem (
        @Id
        @GeneratedValue
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