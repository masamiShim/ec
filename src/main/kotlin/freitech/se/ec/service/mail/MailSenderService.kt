package freitech.se.ec.service.mail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class MailSenderService {

    @Autowired
    private lateinit var mailSender: MailSender

    @Value("\${spring.mail.src}")
    private val src: String = "example@gmail.com"

    fun send(subject: String, content: String, dist: String) {

        val message = SimpleMailMessage().apply {
            setFrom(src)
            setTo(dist)
            setSubject(subject)
            setText(content)
        }
        mailSender.send(message)
    }
}
