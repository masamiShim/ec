package freitech.se.ec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class EcApplication

fun main(args: Array<String>) {
    runApplication<EcApplication>(*args)
}
