package freitech.se.ec.config

import org.apache.ibatis.session.SqlSession
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jdbc.core.DataAccessStrategy
import org.springframework.data.jdbc.mybatis.MyBatisDataAccessStrategy

@Configuration
class SpringDataJdbcConfig {

    @Bean
    @Primary
    fun mybatisDataAccessStrategy(sqlSession: SqlSession): DataAccessStrategy{
        return MyBatisDataAccessStrategy(sqlSession)
    }
}
