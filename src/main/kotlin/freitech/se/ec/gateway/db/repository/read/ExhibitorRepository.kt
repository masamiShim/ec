package freitech.se.ec.gateway.db.repository.read

import freitech.se.ec.gateway.db.mo.Exhibitor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExhibitorRepository : JpaRepository<Exhibitor, Long> {
}
