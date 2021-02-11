package ru.kniazev.surf.exception

import DatesInputException
import InputNotOnlyInteger
import NotEqualCountBracketsException

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WebRestControllerAdvice {
    @ExceptionHandler(NotEqualCountBracketsException::class)
    fun handle(ex: NotEqualCountBracketsException): ResponseEntity<String> {
        return ResponseEntity("The math task has not balance \"(\" and \")\"",HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DatesInputException::class)
    fun handle(ex: DatesInputException): ResponseEntity<String> {
        return ResponseEntity("Params has error - dateFrom more than dateTo", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ArithmeticException::class)
    fun handle(ex: ArithmeticException): ResponseEntity<String> {
        return ResponseEntity("The math task has sivision by zero", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InputNotOnlyInteger::class)
    fun handle(ex: InputNotOnlyInteger): ResponseEntity<String> {
        return ResponseEntity("The math task has not only integers. It version can math only integers.", HttpStatus.BAD_REQUEST)
    }
}