package freitech.se.ec.repository.read

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations.insertInto
import com.ninja_squad.dbsetup.Operations.sql
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import freitech.se.ec.EcApplicationTests
import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.gateway.db.table.ItemTable.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import javax.sql.DataSource

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableAutoConfiguration
@ActiveProfiles("test")
class ItemReadRepositoryTest: EcApplicationTests() {

    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var itemRepository: ItemRepository


    companion object {
        val DELETE_ITEM: Operation = sql("DELETE FROM item WHERE id = 1")

        val INSERT_ITEM: Operation = insertInto("item")
                .row()
                .column(Id.colName, 1)
                .column(ExhibitorId.colName, 1)
                .column(Name.colName, "hogeItem")
                .column(Code.colName, "121212")
                .column(Price.colName, 2000)
                .column(Quantity.colName, 20)
                .column(Comment.colName, "test Comment")
                .column(Created.colName, LocalDateTime.now())
                .column(CreatedBy.colName, 1)
                .column(Modified.colName, LocalDateTime.now())
                .column(ModifiedBy.colName, 2)
                .column(Deleted.colName, null)
                .end()
                .build()
    }


    @Before
    fun setUp() {
        val setup = DbSetup(DataSourceDestination.with(dataSource), INSERT_ITEM)
        setup.launch()
    }

    @Test
    fun findByPk_test() {
        itemRepository.findById(freitech.se.ec.mo.Id(1)).orElseThrow{ NotFoundException("") }
    }

    @Test(expected = NotFoundException::class)
    fun findByPk_Not_Found() {
        itemRepository.findById(freitech.se.ec.mo.Id(2)).orElseThrow { NotFoundException("") }
    }

    @After
    fun tearDown() {
        val setup = DbSetup(DataSourceDestination.with(dataSource), DELETE_ITEM)
        setup.launch()

    }
}
