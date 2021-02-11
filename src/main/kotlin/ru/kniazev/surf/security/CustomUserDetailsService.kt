package ru.kniazev.surf.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.kniazev.surf.model.User
import ru.kniazev.surf.repository.UserRepository

@Service
class CustomUserDetailsService(
        @Autowired
        private val dao: UserRepository
): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): org.springframework.security.core.userdetails.UserDetails? {
        val myUser: User = dao.findByLogin(userName) ?: throw UsernameNotFoundException("Unknown user: $userName")
        return org.springframework.security.core.userdetails.User.builder()
                .username(myUser.login)
                .password(myUser.password)
                .roles(myUser.role)
                .build()
    }
}