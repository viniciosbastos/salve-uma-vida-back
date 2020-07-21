package br.com.wb.salvaumavida.models

import java.util.*

data class Campaign (
        var id: Int,
        var title: String,
        var item: String,
        var goal: Int,
        var progress: Float,
        var limitDate: Date,
        var creator: User
){

}