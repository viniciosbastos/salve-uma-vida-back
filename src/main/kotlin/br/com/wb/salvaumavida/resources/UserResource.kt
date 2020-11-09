package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.CampaignDTO
import br.com.wb.salvaumavida.dto.CampaignItemDTO
import br.com.wb.salvaumavida.dto.UserDTO
import br.com.wb.salvaumavida.dto.response.NewUserResponse
import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.entitiies.mapToDTO
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.CampaignService
import br.com.wb.salvaumavida.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
class UserResource (
        private val userService: UserService,
        private val campaignService: CampaignService
){

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Int): Response {
        return try {
            Response.Success(userService.find(id))
        } catch (ex: NotFoundException) {
            Response.Error(ex.message!!, ex.cause.toString())
        }
    }

    @PostMapping("/signup")
    fun newUser(@RequestBody user: UserDTO): Response {
        return try {
            val savedUser = userService.createUser(user)
            return Response.Success(NewUserResponse(savedUser.email, savedUser.type))
        } catch (exception: Exception) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }

    @PutMapping("/user")
    fun updateUser(@RequestBody user: UserDTO): UserDTO {
        userService.saveUser(user)
        return user
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: Int): String {
        userService.deleteUser(id)
        return "deleted"
    }

    @GetMapping("/user/{id}/campaigns")
    fun getUserCampaigns(@PathVariable id: Int,
                         @RequestParam("title", defaultValue = "") title: String,
                         @RequestParam("itemDescription", defaultValue = "") itemDescription: String
    ): Response {
        return try {
            Response.Success(campaignService.findUserCampaigns(id, title, itemDescription).map { it.mapToDTO() })
        } catch (exception: NotFoundException) {
            Response.Error(exception.message!!, exception.cause.toString())
        }
    }
}