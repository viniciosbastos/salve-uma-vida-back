package br.com.wb.salvaumavida.models

data class User (
        val id: Int,
        val name: String,
        val email: String,
        val detail: String,
        val address: String,
        val addressLatitude: Double,
        val addressLongitude: Double,
        val type: UserType
){
}