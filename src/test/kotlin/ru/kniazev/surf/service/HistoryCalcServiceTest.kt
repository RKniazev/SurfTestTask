package ru.kniazev.surf.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.kniazev.surf.SurfApplication
import ru.kniazev.surf.model.CalcEntity
import ru.kniazev.surf.repository.UserRepository
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SurfApplication::class])
internal class HistoryCalcServiceTest {
    @Autowired
    lateinit var service: HistoryCalcService
    @Autowired
    lateinit var repUser: UserRepository

    @Test
    fun `Get calcHistoryBetweenDate`() {
        var result = service.getCalcHistory("2021-02-07", "2021-02-08", null, null)

        assertEquals(2,result.size)

        assertEquals(LocalDate.parse("2021-02-07"), result[0].date)
        assertEquals("2+3", result[0].request)
        assertEquals("5", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

        assertEquals(LocalDate.parse("2021-02-08"), result[1].date)
        assertEquals("3+2", result[1].request)
        assertEquals("5", result[1].response)
        assertEquals(repUser.findById(2).get(), result[1].user)

    }

    @Test
    fun `Get calcHistoryBetweenDateAndUser`() {
        var result = service.getCalcHistory("2021-02-07", "2021-02-08", null, "surf")

        assertEquals(1,result.size)

        assertEquals(LocalDate.parse("2021-02-07"), result[0].date)
        assertEquals("2+3", result[0].request)
        assertEquals("5", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

    }

    @Test
    fun `Get calcHistoryBetweenDateAndRequest`() {
        var result = service.getCalcHistory("2021-02-06", "2021-02-08", "2+2", null)

        assertEquals(1,result.size)

        assertEquals(LocalDate.parse("2021-02-06"), result[0].date)
        assertEquals("2+2", result[0].request)
        assertEquals("4", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

    }

    @Test
    fun `Get calcHistoryBetweenDateAndRequestAndUser`() {
        var result = service.getCalcHistory("2021-02-06", "2021-02-07", "2+2", "surf")

        assertEquals(1,result.size)

        assertEquals(LocalDate.parse("2021-02-06"), result[0].date)
        assertEquals("2+2", result[0].request)
        assertEquals("4", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

    }

    @Test
    fun `Get calcHistoryByRequestAndUser`() {
        var result = service.getCalcHistory(null, null, "3+3", "other")

        assertEquals(1,result.size)

        assertEquals(LocalDate.parse("2021-02-09"), result[0].date)
        assertEquals("3+3", result[0].request)
        assertEquals("6", result[0].response)
        assertEquals(repUser.findById(2).get(), result[0].user)

    }
    @Test
    fun `Get calcHistoryByRequest`() {
        var result = service.getCalcHistory(null, null, "3+2",null )

        assertEquals(1,result.size)

        assertEquals(LocalDate.parse("2021-02-08"), result[0].date)
        assertEquals("3+2", result[0].request)
        assertEquals("5", result[0].response)
        assertEquals(repUser.findById(2).get(), result[0].user)

    }

    @Test
    fun `Get calcHistoryByUser`() {
        var result = service.getCalcHistory(null, null, null, "surf")

        assertEquals(2,result.size)

        assertEquals(LocalDate.parse("2021-02-06"), result[0].date)
        assertEquals("2+2", result[0].request)
        assertEquals("4", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

        assertEquals(LocalDate.parse("2021-02-07"), result[1].date)
        assertEquals("2+3", result[1].request)
        assertEquals("5", result[1].response)
        assertEquals(repUser.findById(1).get(), result[1].user)

    }
    @Test
    fun `Get calcHistoryWithoutParams`() {
        var result = service.getCalcHistory(null, null, null, null)

        assertEquals(4,result.size)

        assertEquals(LocalDate.parse("2021-02-06"), result[0].date)
        assertEquals("2+2", result[0].request)
        assertEquals("4", result[0].response)
        assertEquals(repUser.findById(1).get(), result[0].user)

        assertEquals(LocalDate.parse("2021-02-07"), result[1].date)
        assertEquals("2+3", result[1].request)
        assertEquals("5", result[1].response)
        assertEquals(repUser.findById(1).get(), result[1].user)

        assertEquals(LocalDate.parse("2021-02-08"), result[2].date)
        assertEquals("3+2", result[2].request)
        assertEquals("5", result[2].response)
        assertEquals(repUser.findById(2).get(), result[2].user)

        assertEquals(LocalDate.parse("2021-02-09"), result[3].date)
        assertEquals("3+3", result[3].request)
        assertEquals("6", result[3].response)
        assertEquals(repUser.findById(2).get(), result[3].user)

    }
}