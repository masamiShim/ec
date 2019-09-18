package freitech.se.ec.controller

import freitech.se.ec.exception.BadRequestException
import freitech.se.ec.response.ErrorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.NoHandlerFoundException
import java.time.LocalDateTime

@ControllerAdvice
class EcExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(EcExceptionHandler::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [BadRequestException::class])
    fun badRequestHandler(e: BadRequestException, handler: HandlerMethod): ResponseEntity<ErrorResponse> {
        return ErrorResponse.create(HttpStatus.BAD_REQUEST, e)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun notReadable(e: HttpMessageNotReadableException, handler: HandlerMethod): ResponseEntity<ErrorResponse> {
        return ErrorResponse.create(HttpStatus.BAD_REQUEST, e)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun notFound(e: NoHandlerFoundException, handler: HandlerMethod): ResponseEntity<ErrorResponse> {
        return ErrorResponse.create(HttpStatus.NOT_FOUND, e)
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    fun methodNotAllowed(e: HttpRequestMethodNotSupportedException, handler: HandlerMethod): ResponseEntity<ErrorResponse> {
        return ErrorResponse.create(HttpStatus.METHOD_NOT_ALLOWED, e)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [Exception::class, RuntimeException::class])
    fun unknownExceptionHandler(e: Exception, handler: HandlerMethod): ResponseEntity<ErrorResponse> {
        logger.warn("unknownError has occurred")
        logger.info("datetime: ${LocalDateTime.now()}, message: ${e.message}, class =  ${handler.javaClass}, method: ${handler.method.name}")
        e.printStackTrace()
        return ErrorResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, e)
    }

}
