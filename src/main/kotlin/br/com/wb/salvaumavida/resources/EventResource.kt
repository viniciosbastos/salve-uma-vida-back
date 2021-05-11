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

    @DeleteMapping("/event/{id}")
    fun deleteEvent(@PathVariable id: Int): Response {
        service.deleteEvent(id)
        return Response.Success("Campanha excluída.")
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

    @GetMapping("/events")
    fun getLoggedUserEvents(principal: Principal,
                     @RequestParam("param", defaultValue = "") param: String
    ): Response {
        return try {
            Response.Success(service.findLoggedUserEvents(principal.name, param).map { it.mapToDTO() })
        } catch (exception: NotFoundException) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }

    @PutMapping("/event/close/{id}")
    fun closeEvent(@PathVariable id: Int): Response {
        return try {
            service.closeEvent(id)
            Response.Success("Campanha fechada")
        } catch(ex: Exception) {
            Response.Error(ex.message!!, "")
        }
    }
}