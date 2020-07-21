package br.com.wb.salvaumavida.models

data class Event (
        val id: Int,
        val title: String,
        val address: String,
        val addressLatitude: Double,
        val addressLongitude: Double,
        val participants: Int,
        val creator: User
){
}