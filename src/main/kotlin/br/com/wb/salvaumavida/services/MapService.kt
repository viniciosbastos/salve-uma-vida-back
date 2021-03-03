package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.LocationDTO
import org.springframework.stereotype.Service

@Service
class MapService (
        private val userService: UserService,
        private val eventService: EventService
        ){

    fun getMapInfo(): List<LocationDTO> {
        var locations = mutableListOf<LocationDTO>().apply {
            addAll(userService.findNGOLocations())
            addAll(eventService.findEventLocation())
        }
        return locations
    }

}