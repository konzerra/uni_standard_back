package com.konzerra.uni_standard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UniStandardBackApplication

fun main(args: Array<String>) {
    runApplication<UniStandardBackApplication>(*args)
}
