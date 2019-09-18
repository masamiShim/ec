package freitech.se.ec.param

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.vo.Email

data class PasswordReminderParam(val email: String) {
    fun toEmail(): Email {
        // TODO: ここらへん暗号化系統はごにょる
        try {
            return Email(this.email)
        } catch (e: IllegalArgumentException) {
            throw BadRequestException(e.message!!)
        }
    }
}
