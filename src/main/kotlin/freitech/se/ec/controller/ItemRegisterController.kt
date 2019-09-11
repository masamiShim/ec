package freitech.se.ec.controller

import freitech.se.ec.param.ItemRegisterFormParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemRegisterController {

    @PostMapping("/owner/register")
    fun register(
            @RequestBody name: String,
            @RequestBody code: String,
            @RequestBody price: String,
            @RequestBody quantity: String,
            @RequestBody comment: String) {

        val itemRegisterParam = ItemRegisterFormParam(name, code, price, quantity, comment)


    }
}
