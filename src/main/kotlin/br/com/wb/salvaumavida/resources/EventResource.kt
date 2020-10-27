package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.EventDTO
import br.com.wb.salvaumavida.entitiies.mapToDTO
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.EventService
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/event/{id}")
    fun getEvent(@PathVariable id: Int): Response {
        return try {
            Response.Success(service.find(id).mapToDTO())
        } catch (exception: NotFoundException) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }

    @PutMapping("/event")
    fun updateEvent(@RequestBody event: EventDTO): Response {
        return try {
            service.updateEvent(event)
            Response.Success("Evento atualizado com sucesso.")
        } catch (exception: NotFoundException) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }

    @GetMapping("/event/search")
    fun searchEvent(
            @RequestParam("param", defaultValue = "") param: String
    ): Response {
        return try {
            Response.Success(service.searchEvent(param).map { it.mapToDTO() })
        } catch (exception: NotFoundException) {
            Response.Error(exception.message.toString(), exception.cause.toString())
        }
    }

}