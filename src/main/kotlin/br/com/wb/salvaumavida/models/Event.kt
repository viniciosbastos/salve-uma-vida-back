package br.com.wb.salvaumavida.models

data class Event (
        var id: Int,
        var title: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double,
        var participants: Int,
        var creator: User
){
}