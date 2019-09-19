package freitech.se.ec.controller.app.member

import freitech.se.ec.domain.service.write.UserWriteService
import freitech.se.ec.exception.ExistsEmailException
import freitech.se.ec.param.SignInParam
import freitech.se.ec.response.BaseResponse
import freitech.se.ec.response.ErrorResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerMethod

/**
 * サインイン後のログインはAPIのレスポンス受け取る +
 * ログイン用のAPI投げてjwtトークンを保持するぐらいでOK?
 */
@RestController
class SignInController {

    @Autowired
    private lateinit var userWriteService: UserWriteService

    @ExceptionHandler(ExistsEmailException::class)
    fun existsEmailHandler(
            exception: ExistsEmailException,
            handlerMethod: HandlerMethod): ResponseEntity<ErrorResponse> {
        return ErrorResponse.create(HttpStatus.BAD_REQUEST, exception)
    }

    @PostMapping(value = ["/signin"])
    fun signIn(@RequestBody param: SignInParam): ResponseEntity<BaseResponse> {

        userWriteService.save(param)

        return ResponseEntity.ok(BaseResponse("success"))
    }
}
