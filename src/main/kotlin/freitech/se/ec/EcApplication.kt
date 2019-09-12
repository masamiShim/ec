package freitech.se.ec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@SpringBootApplication
@PropertySource(value = ["classpath:application.yml"])
class EcApplication

@Bean
fun propertySourcesPlaceholderConfigurer(): PropertySourcesPlaceholderConfigurer {
    return PropertySourcesPlaceholderConfigurer()
}

fun main(args: Array<String>) {
    runApplication<EcApplication>(*args)
}
