package ru.kniazev.surf.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
        var login: String = "",
        @JsonIgnore
        var password: String = "",
        @JsonIgnore
        var role:String = ""
) :BaseEntity<Long>(){
}
