package br.com.wb.salvaumavida.models

import java.util.Date

data class Chat (
        var id: Int,
        var user1: User,
        var user2: User,
        var createdAt: Date
){
}