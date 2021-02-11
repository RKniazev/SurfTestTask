package ru.kniazev.surf.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.kniazev.surf.model.CalcEntity
import ru.kniazev.surf.service.HistoryCalcService

@RestController
@RequestMapping("/history")
class CalcHistoryController(
        @Autowired val calcService: HistoryCalcService
){
    @GetMapping("/")
    fun getAll(@RequestParam("dateFrom") date1:String?,
               @RequestParam("dateTo") date2:String?,
               @RequestParam("request") requestStr: String?,
               @RequestParam("user") userLogin: String?
    ):List<CalcEntity> = calcService.getCalcHistory(date1, date2, requestStr,userLogin)

}