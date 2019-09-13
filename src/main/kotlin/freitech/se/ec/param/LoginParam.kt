package freitech.se.ec.param

import freitech.se.ec.config.NoArg

@NoArg
data class LoginParam(val username: String, val password: String) {
}
