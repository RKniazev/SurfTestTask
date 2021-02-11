package ru.kniazev.surf.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.kniazev.surf.model.CalcEntity
import ru.kniazev.surf.model.User
import java.time.LocalDate

interface CalcRepository: JpaRepository<CalcEntity, Long> {
    fun findByDate(date:LocalDate) : List<CalcEntity>
    fun findByRequest(request:String) : List<CalcEntity>
    fun findByUser(user:User) : List<CalcEntity>

    @Query("" +
            "SELECT * FROM history_calc WHERE " +
            "(:request is null or request = :request)" +
            "and (:userId is null or user_id = :userId)"
            , nativeQuery = true)
    fun findByRequestAndUser(
            @Param("request")request:String?,
            @Param("userId")userId: Long?): List<CalcEntity>

    @Query("" +
            "SELECT * FROM history_calc WHERE " +
            "(date BETWEEN :dateFrom AND :dateTo)" +
            "and (:request is null or request = :request)" +
            "and (:userId is null or user_id = :userId)"
            , nativeQuery = true)
    fun findByRequestAndUserAndDate(
            @Param("dateFrom")dateFrom: LocalDate,
            @Param("dateTo")dateTo: LocalDate,
            @Param("request")request:String?,
            @Param("userId")userId: Long?): List<CalcEntity>

}