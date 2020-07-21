package br.com.wb.salvaumavida.models

data class User (
        var id: Int?,
        var name: String,
        var email: String,
        var detail: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double,
        var type: UserType
){
}