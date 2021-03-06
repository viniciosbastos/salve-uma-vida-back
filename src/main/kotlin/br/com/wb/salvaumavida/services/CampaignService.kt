package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.dto.DonationDTO
import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.entitiies.CampaignItem
import br.com.wb.salvaumavida.entitiies.canBeClosed
import br.com.wb.salvaumavida.exceptions.CantBeClosedException
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.CampaignRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CampaignService (
        private val repository: CampaignRepository,
        private val userService: UserService,
        private val campaignItemService: CampaignItemService
){

    fun findCampaign(id: Int, title: String = "", itemDescription: String = ""): Campaign {
        return repository.findById(id).orElseThrow { NotFoundException("Campanha não encontrada") }
    }

    fun findUserCampaigns(userId: Int, param: String): List<Campaign> {
        return repository
                .findUserCampaignsByFilter(userId, param)
                .orElseThrow{ NotFoundException("Nenhuma campanha encontrada.") }
    }

    fun findUserCampaigns(username: String, param: String): List<Campaign> {
        val user = userService.find(username)
        return repository
                .findUserCampaignsByFilter(user.id!!, param)
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

    fun updateCampaign(dto: CampaignDTO) {
        val campaign = findCampaign(dto.id!!)
        campaign.apply {
            title = dto.title
            items = dto.items.map{ CampaignItem(it.id, it.description, it.goal, it.progress, campaign, it.unit) }
            limitDate = dto.limitDate
            description = dto.description
        }
        repository.save(campaign)
    }

    fun searchCampaign(param: String): List<Campaign> {
        return repository
                .findCampaignsByFilter(param)
                .orElseThrow { NotFoundException("Nenhuma campanha encontrada") }
    }

    fun closeCampaign(campaignId: Int) {
        val campaign = findCampaign(campaignId)
        if (campaign.canBeClosed()) {
            campaign.open = false
            repository.save(campaign)
        } else {
            throw CantBeClosedException("Campanha não pode ser fechada.")
        }
    }

    fun registerCampaignDonation(donation: DonationDTO) {
        val campaignItem = campaignItemService.findById(donation.itemId)
        campaignItem.progress += donation.donationQuantity
        campaignItemService.save(campaignItem)
    }
}