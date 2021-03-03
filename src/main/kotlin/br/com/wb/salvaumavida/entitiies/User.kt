package br.com.wb.salvaumavida.entitiies

import br.com.wb.salvaumavida.dto.LocationDTO
import br.com.wb.salvaumavida.dto.LocationEnum
import br.com.wb.salvaumavida.dto.UserDTO
import javax.persistence.*

@Entity
@Table(name = "suv_user")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        var name: String,
        var email: String,
        var password: String,
        var detail: String?,
        var address: String?,
        var addressLatitude: Double?,
        var addressLongitude: Double?,
        @Enumerated(EnumType.STRING)
        var type: UserType,
        var image: String,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        var campaigns: List<Campaign>?,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        var events: List<Event>?

){

}

fun User.mapToDto(): UserDTO = UserDTO (
        name = this.name,
        email = this.email,
        password = this.password,
        detail = this.detail,
        address = this.address,
        addressLatitude = this.addressLatitude,
        addressLongitude = this.addressLongitude,
        type = this.type,
        image = this.image
)

fun User.mapToLocationDto(): LocationDTO = LocationDTO (
        type = LocationEnum.NGO,
        latitude = this.addressLatitude!!,
        longitude = this.addressLongitude!!,
        description = this.name,
        id = this.id!!
)