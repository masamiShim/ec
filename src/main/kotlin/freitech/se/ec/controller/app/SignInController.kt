package freitech.se.ec.controller.app

import freitech.se.ec.param.SignInParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController {

    @PostMapping
    fun signIn(@RequestBody param: SignInParam) {

    }
}
