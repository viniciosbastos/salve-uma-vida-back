package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.entitiies.CampaignItem
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.CampaignRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional

@Service
class CampaignService (
        private val repository: CampaignRepository,
        private val userService: UserService,
        private val campaignItemService: CampaignItemService
){

    fun findCampaign(id: Int, title: String = "", itemDescription: String = ""): Campaign {
        return repository.findById(id).orElseThrow { RuntimeException("não encontrado") }
    }

    fun findUserCampaigns(userId: Int, title: String = "", itemDescription: String = ""): List<Campaign> {
        return repository
                .findUserCampaignsByFilter(userId, title, itemDescription)
                .orElseThrow{ NotFoundException("Nenhuma campanha encontrada.") }
    }

    fun findUserCampaigns(username: String, title: String, itemDescription: String): List<Campaign> {
        val user = userService.find(username)
        return repository
                .findUserCampaignsByFilter(user.id!!, title, itemDescription)
                .orElseThrow{ NotFoundException("Nenhuma campanha encontrada.") }
    }

    @Transactional
    fun saveCampaign(campaignDto: CampaignDTO, username: String) {
        val user = userService.find(username)
        val campaign = Campaign(
                title = campaignDto.title,
                limitDate = campaignDto.limitDate,
                items = listOf(),
                user = user,
                id = null,
                description = campaignDto.description
        )
        repository.save(campaign)
        campaignItemService.saveAll(campaignDto.items.map{ CampaignItem(
                id = null,
                description = it.description,
                goal = it.goal,
                progress = it.progress,
                campaign = campaign,
                unit = it.unit
        )})
    }

    fun deleteCampaign(id: Int) {
        repository.deleteById(id)
    }

    fun updateCampaign(id: Int, dto: CampaignDTO) {
        val campaign = findCampaign(id)
        campaign.apply {
            title = dto.title
            items = dto.items.map{ CampaignItem(it.id, it.description, it.goal, it.progress, campaign, it.unit) }
            limitDate = dto.limitDate
        }
        repository.save(campaign)
    }

    fun searchCampaign(title: String, itemDescription: String): List<Campaign> {
        return repository
                .findCampaignsByFilter(title, itemDescription)
                .orElseThrow { NotFoundException("Nenhuma campanmha encontrada") }
    }
}