package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.dto.response.NewUserResponse
import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.exceptions.NotFoundException
import br.com.wb.salvaumavida.models.Response
import br.com.wb.salvaumavida.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
class UserResource (
        @Autowired private val userService: UserService
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
    fun newUser(@RequestBody user: User): Response {
        val savedUser = userService.saveUser(user)
        return Response.Success(NewUserResponse(savedUser.email, savedUser.type))
    }

    @PutMapping("/user")
    fun updateUser(@RequestBody user: User): User {
        userService.saveUser(user)
        return user
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: Int): String {
        userService.deleteUser(id)
        return "deleted"
    }
}