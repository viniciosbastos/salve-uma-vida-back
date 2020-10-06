package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.Event
import java.util.*

data class EventDTO (
        var id: Int?,
        var title: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double,
        var image: String,
        var description: String,
        var date: Date
){
}

fun EventDTO.mapToEntity(): Event = Event(
        id = this.id,
        title = this.title,
        address = this.address,
        addressLatitude = this.addressLatitude,
        addressLongitude = this.addressLongitude,
        image = this.image,
        description = this.description,
        date = this.date
)