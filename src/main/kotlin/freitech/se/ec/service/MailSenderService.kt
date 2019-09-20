package freitech.se.ec.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class MailSenderService {

    @Autowired
    lateinit var mailSender: MailSender

    @Value("\${spring.mail.src}")
    val src: String =""

    fun sendRemind(dist: String){
        val message = SimpleMailMessage().apply {
            setFrom(src)
            setTo(dist)
            setSubject("パスワード再設定メール")
            setText("""
               パスワード再設定用のメールです。
               以下のリンクをクリックしてパスワードを再設定してください。
            """)
        }
    }
}