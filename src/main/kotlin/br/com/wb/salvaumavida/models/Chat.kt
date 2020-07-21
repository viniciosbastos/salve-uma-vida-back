package br.com.wb.salvaumavida.models

import java.util.Date

data class Chat (
        val id: Int,
        val user1: User,
        val user2: User,
        val createdAt: Date
){
}