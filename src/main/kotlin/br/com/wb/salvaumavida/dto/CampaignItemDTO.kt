package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.UnitType


data class CampaignItemDTO (
        var id: Int?,
        var description: String,
        var goal: Float,
        var progress: Float,
        var unit: UnitType
){

}