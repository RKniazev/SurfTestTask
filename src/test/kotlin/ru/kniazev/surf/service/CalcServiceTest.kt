package ru.kniazev.surf.service

import InputNotOnlyInteger
import NotEqualCountBracketsException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.kniazev.surf.SurfApplication
import ru.kniazev.surf.repository.CalcRepository
import ru.kniazev.surf.repository.UserRepository
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SurfApplication::class])
internal class CalcServiceTest {
    @Autowired
    lateinit var service: CalcService
    @Autowired
    lateinit var repCalc: CalcRepository

    @Test
    fun `Check adding to repository`() {
        val allRepBefore = repCalc.findAll()
        service.start("2+2+2","surf")
        val allRepAfter = repCalc.findAll()
        val result = allRepAfter.minus(allRepBefore)
        assertEquals(1, result.size)

        assertEquals(LocalDate.now(), result[0].date)
        assertEquals("2+2+2", result[0].request)
        assertEquals("6", result[0].response)
        assertEquals("surf", result[0].user?.login)
    }

    @Test
    fun `Check exception NotEqualCountBracketsException`() {
        val exception = Assertions.assertThrows(NotEqualCountBracketsException::class.java) {
            service.start("2+(2+2", "surf")
        }
        Assertions.assertEquals("The math task has not balance \"(\" and \")\"", exception.message)
    }

    @Test
    fun `Check exception for InputNotOnlyInteger for input with dot`() {
        val exception = Assertions.assertThrows(InputNotOnlyInteger::class.java) {
            service.start("2+2.5+2", "surf")
        }
        Assertions.assertEquals("The math task has not only integers. It version can math only integers.", exception.message)
    }

    @Test
    fun `Check exception for InputNotOnlyInteger for input with comma`() {
        val exception = Assertions.assertThrows(InputNotOnlyInteger::class.java) {
            service.start("2+2,5+2", "surf")
        }
        Assertions.assertEquals("The math task has not only integers. It version can math only integers.", exception.message)
    }
}