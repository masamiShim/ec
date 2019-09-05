package freitech.se.ec.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HealthCheckController {

    @GetMapping("/health")
    fun healthy(): String {
        return "isHealthy"
    }
}