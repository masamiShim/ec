package freitech.se.ec.handler

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.response.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(e: Exception): BaseResponse {
        return BaseResponse(e.message!!)
    }

}
