package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.EventDTO
import br.com.wb.salvaumavida.dto.LocationDTO
import br.com.wb.salvaumavida.dto.LocationEnum
import br.com.wb.salvaumavida.dto.mapToEntity
import java.util.*
import javax.persistence.*

@Entity
data class Event (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var title: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double,
        var participants: Int = 0,
        var image: String,
        var description: String,
        var date: Date,
        @ManyToOne
        var user: User? = null,
        var open: Boolean = true
){
}

fun Event.mapToDTO(): EventDTO = EventDTO(
        id = this.id,
        title = this.title,
        address = this.address,
        addressLatitude = this.addressLatitude,
        addressLongitude = this.addressLongitude,
        image = this.image,
        description = this.description,
        date = this.date,
        userId = user?.id,
        open = this.open
)

fun Event.mapToLocationDTO(): LocationDTO = LocationDTO(
        type = LocationEnum.EVENT,
        latitude = this.addressLatitude,
        longitude = this.addressLongitude,
        description = this.title,
        id = this.id!!
)