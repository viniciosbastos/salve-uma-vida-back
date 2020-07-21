package br.com.wb.salvaumavida.resources

import br.com.wb.salvaumavida.models.User
import br.com.wb.salvaumavida.models.UserType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/user")
class UserResource {

    @GetMapping
    fun getUser(): User = User(
            1,
            "",
            "",
            "",
            "",
            0.0,
            0.0,
            UserType.COMMON
    )
}