package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.Event

data class EventDTO (
        var id: Int?,
        var title: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double
){
}

fun EventDTO.mapToEntity(): Event = Event(
        id = this.id,
        title = this.title,
        address = this.address,
        addressLatitude = this.addressLatitude,
        addressLongitude = this.addressLongitude
)