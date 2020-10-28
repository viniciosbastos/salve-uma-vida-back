package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CampaignRepository : JpaRepository<Campaign, Int>{

    fun findByUserId(userId: Int): Optional<List<Campaign>>

    @Query("select c from Campaign c where upper(c.title) like upper(concat('%',:title,'%'))")
    fun findCampaignsByTitle(
            @Param("title") title: String
    ): Optional<List<Campaign>>

    @Query("select c from Campaign c where upper(c.description) like upper(concat('%',:description,'%'))")
    fun findCampaignsByDescription(
            @Param("description") description: String
    ): Optional<List<Campaign>>

    @Query("select c from Campaign c " +
            "inner join CampaignItem ci on ci.campaign = c where upper(ci.description) like upper(concat('%',:itemDescription,'%')) group by c")
    fun findCampaignsByItemDescription(
            @Param("itemDescription") itemDescription: String
    ): Optional<List<Campaign>>
}