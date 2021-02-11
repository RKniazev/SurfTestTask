package ru.kniazev.surf.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kniazev.surf.model.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login:String) : User
}