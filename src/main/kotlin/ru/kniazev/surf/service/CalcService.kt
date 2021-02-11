package ru.kniazev.surf.service

import InputNotOnlyInteger
import NotEqualCountBracketsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kniazev.surf.model.CalcEntity
import ru.kniazev.surf.repository.CalcRepository
import ru.kniazev.surf.repository.UserRepository
import java.time.LocalDate
import java.time.LocalTime

@Service
class CalcService(
        @Autowired val math: MathService,
        @Autowired val rep: CalcRepository,
        @Autowired val userRep: UserRepository
) {
    fun start(string: String,user: String):String{
        validateData(string)
        val calc = CalcEntity()
        calc.date = LocalDate.now()
        calc.time = LocalTime.now()
        calc.request = string
        calc.response = math.calculate(string)
        calc.user = userRep.findByLogin(user)
        rep.saveAndFlush(calc)

        return calc.response
    }

    fun validateData(string: String){
        val regexBrackets = "\\(|\\)"
        if(regexBrackets.toRegex().findAll(string).count()%2!=0){
            throw NotEqualCountBracketsException(null)
        }
        val regexInt = "\\,|\\."
        if(regexInt.toRegex().findAll(string).count()>0){
            throw InputNotOnlyInteger(null)
        }
    }
}