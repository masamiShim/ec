package freitech.se.ec.controller.app.member

import freitech.se.ec.domain.service.write.UserWriteService
import freitech.se.ec.param.SignInParam
import freitech.se.ec.response.BaseResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 *     【入力項目】
 *     仕様:　入力されたアドレスに対してパスワード再設定用のリンク付きメールを送信
 *     再設定用リンクは有効期限付きリンク(覚えがない場合は無効にする手続きを行えるようにする。)
 *     ・メールアドレス
 * </p>
 */
@RestController
class TwoFactorAuthenticateController {

    @Autowired
    private lateinit var userWriteService: UserWriteService

    @PostMapping(value = ["/password/verify/two/factor/authorize"])
    fun authorized(@RequestBody param: SignInParam): ResponseEntity<BaseResponse> {

        userWriteService.save(param)

        return ResponseEntity.ok(BaseResponse("success"))
    }
}
