package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.models.User
import br.com.wb.salvaumavida.models.UserType
import br.com.wb.salvaumavida.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
class UserResource (
        @Autowired private val userService: UserService
){

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Int): Any {
        var result: Any
        try {
            result = userService.find(id)
        } catch (ex: RuntimeException) {
            result = ex.message!!
        }
        return result
    }

    @PostMapping("/user")
    fun newUser(@RequestBody user: User): User {
        userService.saveUser(user)
        return user
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