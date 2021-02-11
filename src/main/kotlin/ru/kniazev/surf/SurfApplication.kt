package ru.kniazev.surf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SurfApplication

fun main(args: Array<String>) {
	runApplication<SurfApplication>(*args)
}
