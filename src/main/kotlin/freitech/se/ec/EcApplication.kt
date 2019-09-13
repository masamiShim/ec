package freitech.se.ec

import freitech.se.ec.config.YamlPropertySourceFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties
@PropertySource(
        factory = YamlPropertySourceFactory::class,
        value = ["classpath:application.yml"])
class EcApplication


fun main(args: Array<String>) {
    val ctx = runApplication<EcApplication>(*args)
    /*
    val env = ctx.environment
    env.propertySources
            .forEach { ps -> println(ps.name + " : " + ps.javaClass) }

    println("Value of `foo.bar` = " + env.getProperty("application.security.token.headerName")!!)
    */
}
