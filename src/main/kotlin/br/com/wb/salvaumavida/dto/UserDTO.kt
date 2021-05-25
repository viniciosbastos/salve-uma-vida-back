package br.com.wb.salvaumavida.dto

import br.com.wb.salvaumavida.entitiies.User
import br.com.wb.salvaumavida.entitiies.UserType

data class UserDTO (
        var id: Int?,
        var name: String,
        var email: String,
        var password: String,
        var detail: String?,
        var address: String?,
        var addressLatitude: Double?,
        var addressLongitude: Double?,
        var type: UserType,
        var image: String
)

fun UserDTO.mapToEntity(): User = User (
        id = null,
        name = this.name,
        email = this.email,
        password = this.password,
        detail = this.detail,
        address = this.address,
        addressLatitude = this.addressLatitude,
        addressLongitude = this.addressLongitude,
        type = this.type,
        image = this.image,
        campaigns = null,
        events = null
)