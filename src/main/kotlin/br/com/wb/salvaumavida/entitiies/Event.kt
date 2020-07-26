package br.com.wb.salvaumavida.entitiies

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Event (
        @Id
        @GeneratedValue
        var id: Int?,
        var title: String,
        var address: String,
        var addressLatitude: Double,
        var addressLongitude: Double,
        var participants: Int = 0,
        @ManyToOne
        var user: User? = null
){
}