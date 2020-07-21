package br.com.wb.salvaumavida.models

data class User (
        val id: Int,
        val name: String,
        val email: String,
        val detail: String,
        val address: String,
        val addressLatitude: Float,
        val addressLongitude: Float,
        val type: UserType
){
}