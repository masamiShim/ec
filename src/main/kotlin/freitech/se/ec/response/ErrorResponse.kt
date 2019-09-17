package freitech.se.ec.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(val code: Int, val message: String, val cause: String? = "unknown error has occurred") {

    companion object {
        fun create(status: HttpStatus, e: Exception): ResponseEntity<ErrorResponse> {
            return ResponseEntity(ErrorResponse(code = status.value(), message = status.reasonPhrase, cause = e.message), status)
        }
    }
}
