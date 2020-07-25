package br.com.wb.salvaumavida.entitiies

import javax.persistence.*

@Entity
data class User (
        @Id
        @GeneratedValue
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

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        var campaigns: List<Campaign>?,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        var events: List<Event>?

){

}