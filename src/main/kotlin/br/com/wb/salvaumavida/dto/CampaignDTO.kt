package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.UnitType
import java.util.*

data class CampaignDTO (
        var id: Int?,
        var title: String,
        var items: List<CampaignItemDTO>,
        var limitDate: Date
){
}