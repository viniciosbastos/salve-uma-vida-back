package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.ImageDTO
import br.com.wb.salvaumavida.entitiies.Image
import br.com.wb.salvaumavida.entitiies.mapToDTO
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.GalleryService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class GalleryResource (
        private val service: GalleryService
        ){

    @GetMapping("/gallery")
    fun getGallery(principal: Principal): Response {
        return try {
            Response.Success(service.findUserImages(principal.name).map { it.mapToDTO() })
        } catch (exception: Exception) {
            Response.Error(exception.message!!, "")
        }
    }

    @PostMapping("/gallery")
    fun addNewImage(principal: Principal,
                    @RequestBody image: ImageDTO): Response {
        return try {
            service.saveImage(principal.name, image)
            Response.Success("Image salva com sucesso.")
        } catch (exception: Exception) {
            Response.Error(exception.message!!, "")
        }
    }

    @DeleteMapping("/gallery/{id}")
    fun deleteImage(@PathVariable("id") id: Int): Response {
        return try {
            service.deleteImage(id)
            Response.Success("Image removida com sucesso.")
        } catch (exception: Exception) {
            Response.Error(exception.message!!, "")
        }
    }

    @GetMapping("/user/{id}/gallery")
    fun getUserGallery(@PathVariable("id") userId: Int): Response {
        return try {
            Response.Success(service.findUserImages(userId).map{ it.mapToDTO() })
        } catch (exception: Exception) {
            Response.Error(exception.message!!, "")
        }
    }

}