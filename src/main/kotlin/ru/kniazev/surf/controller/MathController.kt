package ru.kniazev.surf.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import ru.kniazev.surf.service.CalcService
import java.security.Principal

@RestController
@RequestMapping("/calc")
class MathController(
        @Autowired val calc: CalcService
) {
    @PostMapping("")
    fun add(@RequestBody inputString: String, principal: Principal) = calc.start(inputString,principal.getName())
}