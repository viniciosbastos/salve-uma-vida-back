package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.CampaignService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class CampaignResource (private val service: CampaignService){

    @GetMapping("/campaign/{id}")
    fun getCampaign(@PathVariable id: Int): Response {
        return try {
            Response.Success(service.findCampaign(id))
        } catch (exception: NotFoundException) {
            Response.Error(exception.message.toString(), exception.cause.toString())
        }
    }

    @PostMapping("/campaign")
    fun addCampaign(@RequestBody campaign: CampaignDTO, principal: Principal): Response {
        service.saveCampaign(campaign, principal.name)
        return Response.Success(campaign)

    }

    @PutMapping("/campaign/{id}")
    fun updateCampaign(@RequestBody campaign: CampaignDTO, @PathVariable id: Int): Response {
        service.updateCampaign(id, campaign)
        return Response.Success("Campanha atualizada.")
    }

    @DeleteMapping("/campaign/{id}")
    fun deleteCampaign(@PathVariable id: Int): Response {
        service.deleteCampaign(id)
        return Response.Success("Campanha excluída.")
    }
}