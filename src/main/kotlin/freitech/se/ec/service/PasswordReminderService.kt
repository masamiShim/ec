package freitech.se.ec.service

import freitech.se.ec.domain.service.EcHashService
import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.gateway.db.mo.Id
import freitech.se.ec.gateway.db.mo.ReminderMail
import freitech.se.ec.gateway.db.repository.read.ReminderMailRepository
import freitech.se.ec.gateway.db.repository.read.UserRepository
import freitech.se.ec.service.mail.MailSenderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class PasswordReminderService {

    @Autowired
    private lateinit var mailSenderService: MailSenderService

    @Autowired
    private lateinit var ecHashService: EcHashService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var reminderMailRepository: ReminderMailRepository


    private val Reminder_Url: String = "/password/reset/"

    private val Subject: String = "パスワード再設定メール"
    private val Template: String = """
               パスワード再設定用のメールです。
               以下のリンクをクリックしてパスワードを再設定してください。(有効期限は発行から2時間です)
            """

    fun remind(id: Long) {
        // ユーザ取得
        val user = userRepository.findById(Id(id)).orElseThrow { throw NotFoundException("user not found") }

        // リマインド情報保存
        val code = Random.nextInt(0, 9999).toString().padStart(8, '0');
        val url = Reminder_Url + ecHashService.hashed(code)
        val reminder = ReminderMail(user = user, expired = LocalDateTime.now().plusHours(2), url = url, code = code)
        reminderMailRepository.save(reminder)

        mailSenderService.send(Subject, Template + reminder.url, user.email)

    }
}
