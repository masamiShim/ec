package freitech.se.ec.gateway.db.repository.read

import freitech.se.ec.gateway.db.mo.Id
import freitech.se.ec.gateway.db.mo.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Long> {
}
