package freitech.se.ec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
class EcApplication

fun main(args: Array<String>) {
    runApplication<EcApplication>(*args)
}
