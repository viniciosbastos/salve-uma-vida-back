package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CampaignRepository : JpaRepository<Campaign, Int>{

    fun findByUserId(userId: Int): Optional<List<Campaign>>
}