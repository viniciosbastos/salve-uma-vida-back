package br.com.wb.salvaumavida.dto

data class LocationDTO (
        val type: LocationEnum,
        val latitude: Double,
        val longitude: Double,
        val description: String,
        val id: Int
){
}

enum class LocationEnum {
    NGO, EVENT
}