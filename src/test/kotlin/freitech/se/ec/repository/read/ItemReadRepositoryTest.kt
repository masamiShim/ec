package freitech.se.ec.repository.read

import org.apache.ibatis.javassist.NotFoundException
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemReadRepositoryTest {

    @Autowired
    lateinit var itemReadRepository: ItemReadRepository

    @Test(expected = NotFoundException::class)
    fun findByPk_test(){
        itemReadRepository.findByPk(1)?:throw NotFoundException("hogeeeeeeee")
    }
}
