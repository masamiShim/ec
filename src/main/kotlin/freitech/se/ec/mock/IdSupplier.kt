package freitech.se.ec.mock

import freitech.se.ec.mo.Id

class IdSupplier {
    var counter: Int = 0

    fun createId(): Id {
        return Id(counter++)
    }

}
