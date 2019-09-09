package freitech.se.ec.repository.read

import com.ninja_squad.dbsetup.Operations.insertInto
import com.ninja_squad.dbsetup.Operations.sql
import com.ninja_squad.dbsetup.operation.Operation
import org.apache.ibatis.javassist.NotFoundException
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemReadRepositoryTest {

    @Autowired
    lateinit var datasource: DataSource

    var DELETE_ITEM: Operation = sql("DELETE FROM item WHERE id = 1")

    var INSERT_ITEM: Operation = insertInto("item")
            .row()
            .column("id", 1)
            .column("exhibitor_id", 1)
            .column("name", "hogeItem")
            .column("code", "121212")
            .column("price", 2000)
            .column("quantity", 20)
            .column("created", LocalDateTime.now())
            .column("createdBy", 1)
            .column("modified", LocalDateTime.now())
            .column("modifiedBy", 2)
            .end()
            .build()


    @Autowired
    lateinit var itemReadRepository: ItemReadRepository

    /*
        @Before
        fun setUp() {
            val setup = DbSetup(DataSourceDestination.with(datasource), INSERT_ITEM)
            setup.launch()
        }
    */
    @Test(expected = NotFoundException::class)
    fun findByPk_test() {
        val aa = itemReadRepository.findByPk(1) ?: throw NotFoundException("hogeeeeeeee")
        print(aa.toString())
    }
/*
    @After
    fun tearDown() {
        val setup = DbSetup(DataSourceDestination.with(datasource), DELETE_ITEM)
        setup.launch()

    }
*/
}
