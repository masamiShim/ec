package freitech.se.ec.repository.read

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations.insertInto
import com.ninja_squad.dbsetup.Operations.sql
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.operation.Operation
import freitech.se.ec.exception.NotFoundException
import freitech.se.ec.table.ItemTable.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemReadRepositoryTest {

    @Autowired
    lateinit var datasource: DataSource

    companion object {
        val DELETE_ITEM: Operation = sql("DELETE FROM item WHERE value = 1")

        val INSERT_ITEM: Operation = insertInto("item")
                .row()
                .column(Id.name, 1)
                .column(ExhibitorId.name, 1)
                .column(Name.name, "hogeItem")
                .column(Code.name, "121212")
                .column(Price.name, 2000)
                .column(Quantity.name, 20)
                .column(Comment.name, "test Comment")
                .column(Created.name, LocalDateTime.now())
                .column(CreatedBy.name, 1)
                .column(Modified.name, LocalDateTime.now())
                .column(ModifiedBy.name, 2)
                .column(Deleted.name, null)
                .end()
                .build()
    }


    @Autowired
    lateinit var itemRepository: ItemRepository

    @Before
    fun setUp() {
        val setup = DbSetup(DataSourceDestination.with(datasource), INSERT_ITEM)
        setup.launch()
    }

    @Test
    fun findByPk_test() {
        itemRepository.findById(1).orElseThrow(NotFoundException("hogeeeeeeee")::class::objectInstance)
    }

    @Test(expected = NotFoundException::class)
    fun findByPk_Not_Found() {
        itemRepository.findById(2).orElseThrow(NotFoundException("hogeeeeeeee")::class::objectInstance)
    }

    @After
    fun tearDown() {
        val setup = DbSetup(DataSourceDestination.with(datasource), DELETE_ITEM)
        setup.launch()

    }
}
