package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.EventDTO
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.EventService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class EventResource (
        private val service: EventService
){

    @PostMapping("/event")
    fun addEvent(@RequestBody event: EventDTO, principal: Principal): Response {
        service.saveEvent(event, principal.name)
        return Response.Success("Evento ${event.title} salvo com sucesso.")
    }

}