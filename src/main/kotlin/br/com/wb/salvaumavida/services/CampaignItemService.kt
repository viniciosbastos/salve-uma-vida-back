package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.entitiies.CampaignItem
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.CampaignItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CampaignItemService (
        private val repository: CampaignItemRepository
){

    fun save(item: CampaignItem) {
        repository.save(item)
    }

    fun saveAll(items: List<CampaignItem>) {
        repository.saveAll(items)
    }

    fun findById(id: Int): CampaignItem {
        return repository
                .findById(id)
                .orElseThrow { NotFoundException("Item não encontrado") }
    }
}