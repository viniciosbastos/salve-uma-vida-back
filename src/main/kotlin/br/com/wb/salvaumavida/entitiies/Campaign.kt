package br.com.wb.salvaumavida.entitiies

import java.util.Date
import javax.persistence.*

@Entity
data class Campaign (
        @Id
        @GeneratedValue
        var id: Int?,
        var title: String,
        @OneToMany(mappedBy = "campaign", cascade = [CascadeType.ALL])
        var items: List<CampaignItem>,
        var limitDate: Date,
        @ManyToOne
        var user: User
){

}