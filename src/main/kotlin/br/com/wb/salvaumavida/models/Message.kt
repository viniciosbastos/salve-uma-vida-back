package br.com.wb.salvaumavida.models

import java.util.Date

data class Message (
        val id: Int,
        val text: String,
        val user: User,
        val sent: Date
){
}