package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Campaign
import br.com.wb.salvaumavida.entitiies.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface EventRepository : JpaRepository<Event, Int> {

    fun findByTitle(title: String): Optional<List<Event>>
    fun findByDescription(description: String): Optional<List<Event>>

}