package ru.kniazev.surf.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "history_calc")
class CalcEntity(
        var date: LocalDate? = null,
        var time: LocalTime? = null,
        var request: String = "",
        var response:String = "",
        @ManyToOne
        var user: User? = null
) :BaseEntity<Long>(){
}
