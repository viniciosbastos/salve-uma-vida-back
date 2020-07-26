package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Int> {
}