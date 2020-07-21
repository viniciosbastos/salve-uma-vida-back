package br.com.wb.salvaumavida.models

import java.util.Date

data class Message (
        var id: Int,
        var text: String,
        var user: User,
        var sent: Date
){
}