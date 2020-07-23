package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import org.springframework.data.jpa.repository.JpaRepository

interface CampaignRepository : JpaRepository<Campaign, Int>{

    fun findByUserId(userId: Int): List<Campaign>
}