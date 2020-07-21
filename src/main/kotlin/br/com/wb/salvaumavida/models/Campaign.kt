package br.com.wb.salvaumavida.models

import java.util.*

data class Campaign (
        val id: Int,
        val title: String,
        val item: String,
        val goal: Int,
        val progress: Float,
        val limitDate: Date,
        val creator: User
){

}