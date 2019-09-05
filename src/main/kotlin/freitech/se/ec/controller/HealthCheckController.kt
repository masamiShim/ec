package freitech.se.ec.controller

import freitech.se.ec.response.BaseResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/health")
    fun healthy(): ResponseEntity<BaseResponse> {
        return ResponseEntity.ok(BaseResponse("isHealthy"))
    }
}
