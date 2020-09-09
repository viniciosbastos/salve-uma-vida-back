package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.UserType

data class JwtDTO(
    var token: String,
    var userType: UserType
)