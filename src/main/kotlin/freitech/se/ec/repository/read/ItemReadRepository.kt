package freitech.se.ec.repository.read

import freitech.se.ec.mo.Item
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ItemReadRepository {

    fun findByPk(@Param("id") id: Int): Item?
}
