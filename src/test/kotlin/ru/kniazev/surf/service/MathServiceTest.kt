package ru.kniazev.surf.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.kniazev.surf.SurfApplication

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SurfApplication::class])
internal class MathServiceTest {
    @Autowired lateinit var service: MathService

    @Test
    fun `first should be 21 in 21 * 12`(){
        assertEquals(21,service.findFirst("21*12"))
    }
    @Test
    fun `first should be 21 in 21 mod 12`(){
        assertEquals(21,service.findFirst("21/12"))
    }
    @Test
    fun `first should be 21 in 21 - 12`(){
        assertEquals(21,service.findFirst("21-12"))
    }
    @Test
    fun `first should be 21 in 21 + 12`(){
        assertEquals(21,service.findFirst("21+12"))
    }
    @Test
    fun `last should be 12 in 21 * 12`(){
        assertEquals(12,service.findLast("21*12"))
    }
    @Test
    fun `last should be 12 in 21 mod 12`(){
        assertEquals(12,service.findLast("21/12"))
    }
    @Test
    fun `last should be 12 in 21 - 12`(){
        assertEquals(12,service.findLast("21-12"))
    }
    @Test
    fun `last should be 12 in 21 + 12`(){
        assertEquals(12,service.findLast("21+12"))
    }
    @Test
    fun `Result openBrackets should be 2+2 in 2+(1+1)`(){
        assertEquals("2+2",service.openBrackets("2+(1+1)"))
    }
    @Test
    fun `Result openBrackets should be 2+2 in (2)+(1+1)`(){
        assertEquals("2+2",service.openBrackets("(2)+(1+1)"))
    }
    @Test
    fun `Result openBrackets should be 2+2 in (2)+((1)+1)`(){
        assertEquals("2+2",service.openBrackets("(2)+((1)+1)"))
    }
    @Test
    fun `Result openBrackets should be 2+8 in (2)+((1+1)*4)`(){
        assertEquals("2+8",service.openBrackets("(2)+((1+1)*4)"))
    }
    @Test
    fun `Result openBrackets should be 2+1 in (2)+((1+1)mod2)`(){
        assertEquals("2+1",service.openBrackets("(2)+((1+1)/2)"))
    }
    @Test
    fun `Result calculate should be 21 in (1+2)*(4+(5-6mod3))`(){
        assertEquals("21",service.calculate("(1+2)*(4+(5-6/3))"))
    }
    @Test
    fun `Result calculate should be 15 in (1+2)*(4+(5-6mod3*2))`(){
        assertEquals("15",service.calculate("(1+2)*(4+(5-6/3*2))"))
    }
    @Test
    fun `Next action should be * in 1+2*4+5-6mod3*2`(){
        assertEquals('*',service.findNextAction("1+2*4+5-6/3*2"))
    }
    @Test
    fun `Next action should be mod in 1+2-4+5-6mod3*2`(){
        assertEquals('/',service.findNextAction("1+2-4+5-6/3*2"))
    }
    @Test
    fun `Next action should be + in 1+2-4+5`(){
        assertEquals('+',service.findNextAction("1+2-4+5"))
    }
    @Test
    fun `Next action should be - in 2-4+5`(){
        assertEquals('-',service.findNextAction("2-4+5"))
    }

    @Test
    fun `Result Action after should be 1+8+5-6mod3*2 in 1+2*4+5-6mod3*2`(){
        assertEquals("1+8+5-6/3*2",service.tmpAction("1+2*4+5-6/3*2",'*'))
    }
    @Test
    fun `Result Action after should be 1+2-4+5-2*2 in 1+2-4+5-6mod3*2`(){
        assertEquals("1+2-4+5-2*2",service.tmpAction("1+2-4+5-6/3*2",'/'))
    }
    @Test
    fun `Result Action after should be 3-4+5 in 1+2-4+5`(){
        assertEquals("3-4+5",service.tmpAction("1+2-4+5",'+'))
    }
    @Test
    fun `Result Action after should be 2+5 in 4-2+5`(){
        assertEquals("2+5",service.tmpAction("4-2+5",'-'))
    }
}