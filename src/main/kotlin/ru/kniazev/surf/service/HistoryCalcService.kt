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

    fun getCalcHistory(date1:String?,
                       date2:String?,
                       request: String?,
                       userLogin:String?):List<CalcEntity>{

        val user  = userLogin?.let { repUser.findByLogin(userLogin) }
        val dateFrom = date1?.let { LocalDate.parse(date1) }
        val dateTo = date2?.let { LocalDate.parse(date2) }

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