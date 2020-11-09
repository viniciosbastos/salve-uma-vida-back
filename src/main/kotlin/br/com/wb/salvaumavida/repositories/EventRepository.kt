package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.entitiies.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface EventRepository : JpaRepository<Event, Int> {

    @Query("select e from Event e where upper(e.title) like upper(concat('%',:param,'%')) or upper(e.description) like upper(concat('%',:param,'%'))")
    fun findEventsByFilter(@Param("param") param: String): Optional<List<Event>>

    @Query("select e from Event e where e.user.id= :userId and (upper(e.title) like upper(concat('%',:param,'%')) or upper(e.description) like upper(concat('%',:param,'%')))")
    fun findLoggedUserEventsByFilter(
            @Param("userId") userId: Int,
            @Param("param") param: String
    ): Optional<List<Event>>

}