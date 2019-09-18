package freitech.se.ec.controller.app.member

import freitech.se.ec.domain.service.write.UserWriteService
import freitech.se.ec.param.SignInParam
import freitech.se.ec.response.BaseResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController {

    @Autowired
    private lateinit var userWriteService: UserWriteService

    @PostMapping(value = ["/signIn"])
    fun signIn(@RequestBody param: SignInParam): ResponseEntity<BaseResponse> {

        userWriteService.save(param)

        return ResponseEntity.ok(BaseResponse("success"))
    }
}
