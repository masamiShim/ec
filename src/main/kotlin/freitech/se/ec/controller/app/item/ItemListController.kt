package freitech.se.ec.controller.app.item

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.exception.IllegalPolicyException
import freitech.se.ec.gateway.db.repository.read.ItemRepository
import freitech.se.ec.gateway.db.repository.read.UserRepository
import freitech.se.ec.response.app.item.ItemResponse
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class ItemListController {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @GetMapping("/exhibitor/item/detail/{id}")
    fun getItem(principal: Principal, @PathVariable("id") id: Long?): ItemResponse {
        val user = userRepository.findByEmail(principal.name)
                .orElseThrow { throw NotFoundException("user not found") }
        id?.let { it ->
            val item = itemRepository.findById(it).orElseThrow { throw NotFoundException("item not found") }
            return if (item.content.exhibitorId != user.id) {
                ItemResponse.from(item)
            } else {
                throw IllegalPolicyException("confirm is this your item.")
            }
        }
        throw BadRequestException("id is required")
    }

    fun getList() {

    }
}
