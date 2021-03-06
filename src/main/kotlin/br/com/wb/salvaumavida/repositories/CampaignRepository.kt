package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CampaignRepository : JpaRepository<Campaign, Int>{

    fun findByUserId(userId: Int): Optional<List<Campaign>>

    @Query("select c from Campaign c " +
            "inner join CampaignItem ci on ci.campaign = c " +
            "where c.open is true and (upper(ci.description) like upper(concat('%',:param,'%'))" +
            " or upper(c.description) like upper(concat('%',:param,'%'))" +
            " or upper(c.title) like upper(concat('%',:param,'%'))) group by c")
    fun findCampaignsByFilter(
            @Param("param") param: String
    ): Optional<List<Campaign>>

    @Query("select c from Campaign c " +
            "inner join CampaignItem ci on ci.campaign = c " +
            "where c.user.id = :userId" +
            " and (upper(ci.description) like upper(concat('%',:param,'%'))" +
            " or upper(c.description) like upper(concat('%',:param,'%'))" +
            " or upper(c.title) like upper(concat('%',:param,'%'))) group by c")
    fun findUserCampaignsByFilter(
            @Param("userId") userId: Int,
            @Param("param") param: String
    ): Optional<List<Campaign>>

}