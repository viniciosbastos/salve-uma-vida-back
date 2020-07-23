package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.CampaignItem
import org.springframework.data.jpa.repository.JpaRepository

interface CampaignItemRepository : JpaRepository<CampaignItem, Int>{
}