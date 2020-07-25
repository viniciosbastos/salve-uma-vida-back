package br.com.wb.salvaumavida.dto.response

import br.com.wb.salvaumavida.entitiies.UserType

data class NewUserResponse(
        var username: String,
        var type: UserType
)