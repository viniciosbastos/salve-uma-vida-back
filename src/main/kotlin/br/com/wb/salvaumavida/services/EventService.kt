package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.EventDTO
import br.com.wb.salvaumavida.dto.mapToEntity
import br.com.wb.salvaumavida.entitiies.Event
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService (
        private val repository: EventRepository,
        private val userService: UserService
){

    fun saveEvent(eventDto: EventDTO, username: String) {
        val user = userService.find(username)
        val event = eventDto.mapToEntity()
        event.user = user
        repository.save(event)
    }

    fun find(id: Int): Event {
        return repository.findById(id)
                .orElseThrow { NotFoundException("Nenhum evento encontrado.") }
    }

    fun updateEvent(eventDto: EventDTO) {
        val event = find(eventDto.id!!)
        event.apply {
            title = eventDto.title
            address = eventDto.address
            addressLatitude = eventDto.addressLatitude
            addressLongitude = eventDto.addressLongitude
            description = eventDto.description
            date = eventDto.date
            image = eventDto.image
        }
        repository.save(event)
    }

    fun searchEvent(param: String): List<Event> {
        return repository
                .findEventsByFilter(param)
                .orElseThrow { NotFoundException("Nenhum evento encontrado.") }
    }

    fun findLoggedUserEvents(username: String, param: String): List<Event> {
        val user = userService.find(username)
        return repository
                .findLoggedUserEventsByFilter(user.id!!, param)
                .orElseThrow { NotFoundException("Nenhum evento encontrado.") }
    }
}