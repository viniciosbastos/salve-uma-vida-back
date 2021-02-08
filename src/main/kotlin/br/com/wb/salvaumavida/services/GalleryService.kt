package br.com.wb.salvaumavida.services

import br.com.wb.salvaumavida.dto.ImageDTO
import br.com.wb.salvaumavida.entitiies.Image
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.repositories.GalleryRepository
import org.springframework.stereotype.Service

@Service
class GalleryService (
        private val repository: GalleryRepository,
        private val userService: UserService
        ){

    fun saveImage(username: String, imageDTO: ImageDTO) {
        val user = userService.find(username)
        val image = Image(
                id = null,
                image = imageDTO.image,
                user = user
        )
        repository.save(image)
    }

    fun findUserImages(name: String): List<Image> {
        val user = userService.find(name)
        return repository
                .findByUser_Id(user.id!!)
                .orElseThrow { NotFoundException("Nenhuma imagem encontrada.") }
    }

    fun deleteImage(id: Int) {
        repository.deleteById(id)
    }

    fun findUserImages(userId: Int): List<Image> {
        return repository
                .findByUser_Id(userId)
                .orElseThrow { NotFoundException("Nenhuma imagem encontrada.") }
    }

}