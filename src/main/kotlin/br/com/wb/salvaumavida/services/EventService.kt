package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.EventDTO
import br.com.wb.salvaumavida.dto.mapToEntity
import br.com.wb.salvaumavida.entitiies.Event
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
}