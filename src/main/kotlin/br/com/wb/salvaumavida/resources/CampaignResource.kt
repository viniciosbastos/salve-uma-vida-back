package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.dto.DonationDTO
import br.com.wb.salvaumavida.entitiies.mapToDTO
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.CampaignService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class CampaignResource (private val service: CampaignService){

    @GetMapping("/campaign/search")
    fun searchCampaign(
            @RequestParam("param", defaultValue = "") param: String
    ): Response {
        return try {
            Response.Success(service.searchCampaign(param).map { it.mapToDTO() })
        } catch (exception: NotFoundException) {
            Response.Error(exception.message.toString(), exception.cause.toString())
        }
    }

    @GetMapping("/campaign/{id}")
    fun getCampaign(@PathVariable id: Int): Response {
        return try {
            Response.Success(service.findCampaign(id).mapToDTO())
        } catch (exception: NotFoundException) {
            Response.Error(exception.message.toString(), exception.cause.toString())
        }
    }

    @GetMapping("/campaigns")
    fun getCampaigns(principal: Principal,
                     @RequestParam("param", defaultValue = "") param: String
    ): Response {
        return try {
            Response.Success(service.findUserCampaigns(principal.name, param).map { it.mapToDTO() })
        } catch (exception: NotFoundException) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }

    @PostMapping("/campaign")
    fun addCampaign(@RequestBody campaign: CampaignDTO, principal: Principal): Response {
        service.saveCampaign(campaign, principal.name)
        return Response.Success("Campanha ${campaign.title} salva com sucesso.")

    }

    @PutMapping("/campaign")
    fun updateCampaign(@RequestBody campaign: CampaignDTO): Response {
        service.updateCampaign(campaign)
        return Response.Success("Campanha atualizada.")
    }

    @DeleteMapping("/campaign/{id}")
    fun deleteCampaign(@PathVariable id: Int): Response {
        service.deleteCampaign(id)
        return Response.Success("Campanha excluída.")
    }

    @PutMapping("/campaign/close/{id}")
    fun closeCampaign(@PathVariable id: Int): Response {
        return try {
            service.closeCampaign(id)
            Response.Success("Campanha fechada")
        } catch(ex: Exception) {
            Response.Error(ex.message!!, "")
        }
    }

    @PostMapping("/donation")
    fun registerCampaignDonation(@RequestBody donation: DonationDTO): Response {
        return try {
            service.registerCampaignDonation(donation)
            Response.Success("sucesso")
        } catch(ex: Exception) {
            Response.Error(ex.message!!, "")
        }
    }
}