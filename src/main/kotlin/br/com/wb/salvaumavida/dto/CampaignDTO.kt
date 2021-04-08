package br.com.wb.salvaumavida.dto

import java.util.*

data class CampaignDTO (
        var id: Int?,
        var title: String,
        var items: List<CampaignItemDTO>,
        var limitDate: Date,
        var description: String,
        var userImage: String?,
        var userId: Int?,
        var open: Boolean
){
}