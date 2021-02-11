package ru.kniazev.surf.service

import DatesInputException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kniazev.surf.model.CalcEntity
import ru.kniazev.surf.repository.CalcRepository
import ru.kniazev.surf.repository.UserRepository
import java.time.LocalDate

@Service
class HistoryCalcService(@Autowired val rep:CalcRepository,
                         @Autowired val repUser:UserRepository) {

    fun getCalcHistory(dateStrFrom:String?,
                       dareStrTo:String?,
                       request: String?,
                       userLogin:String?):List<CalcEntity>{

        val user  = userLogin?.let { repUser.findByLogin(userLogin) }
        val dateFrom = dateStrFrom?.let { LocalDate.parse(dateStrFrom) }
        val dateTo = dareStrTo?.let { LocalDate.parse(dareStrTo) }

        if (dateFrom != null && dateTo != null){
            if (dateFrom>dateTo){
                throw DatesInputException(null)
            }
            return rep.findByRequestAndUserAndDate(
                    dateFrom,
                    dateTo,
                    request,
                    user?.id)
        }

        return rep.findByRequestAndUser(request, user?.id)
    }
}