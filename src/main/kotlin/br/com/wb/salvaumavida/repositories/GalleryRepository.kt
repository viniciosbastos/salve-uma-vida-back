package br.com.wb.salvaumavida.repositories

import br.com.wb.salvaumavida.entitiies.Image
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GalleryRepository : JpaRepository<Image, Int>{

    fun findByUser_Id(userId: Int): Optional<List<Image>>
}