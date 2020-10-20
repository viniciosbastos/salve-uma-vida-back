package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CampaignRepository : JpaRepository<Campaign, Int>{

    fun findByUserId(userId: Int): Optional<List<Campaign>>

    @Query("select c from Campaign c " +
            "inner join CampaignItem ci on ci.campaign = c where c.title like %:title% and ci.description like %:itemDescription% group by c")
    fun findCampaignsByFilter(
            @Param("title") title: String,
            @Param("itemDescription") itemDescription: String): Optional<List<Campaign>>
}