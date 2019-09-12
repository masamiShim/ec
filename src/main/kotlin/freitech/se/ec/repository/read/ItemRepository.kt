package freitech.se.ec.repository.read

import freitech.se.ec.mo.Id
import freitech.se.ec.mo.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Id> {
}
