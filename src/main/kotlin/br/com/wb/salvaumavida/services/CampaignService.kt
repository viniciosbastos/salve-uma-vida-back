package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.entitiies.CampaignItem
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

    fun findCampaign(id: Int): Campaign {
        return repository.findById(id).orElseThrow { RuntimeException("não encontrado") }
    }

    fun findUserCampaigns(userId: Int): List<Campaign> {
        return repository.findByUserId(userId)
    }

    @Transactional
    fun saveCampaign(campaignDto: CampaignDTO) {
        val user = userService.find(1)
        val campaign = Campaign(
                title = campaignDto.title,
                limitDate = campaignDto.limitDate,
                items = listOf(),
                user = user,
                id = null
        )
        repository.save(campaign)
        campaignDto.items.forEach {
            val item = CampaignItem(
                    id = null,
                    description = it.description,
                    goal = it.goal,
                    progress = it.progress,
                    campaign = campaign,
                    unit = it.unit
            )
            campaignItemService.save(item)
        }

    }

    fun deleteCampaign(id: Int) {
        repository.deleteById(id)
    }
}