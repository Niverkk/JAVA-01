package info.niverkk.course14.work02.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author JKXAING on 2021/3/6
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * datasource 配置
     * @return
     */
    @Bean
    public DataSource ds1(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("Xiangjk666");
        dataSource.setJdbcUrl("jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/jeek");

        //Connection connection = dataSource.getConnection();
        //return connection;
        return dataSource;
    }
    @Bean
    public DataSource ds2(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("Xiangjk666");
        dataSource.setJdbcUrl("jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/jeek2");

        //Connection connection = dataSource.getConnection();
        //return connection;
        return dataSource;
    }

    //@Bean
    //public JdbcTemplate jt1(){
    //    return new JdbcTemplate(ds1());
    //}
    //
    //@Bean
    //public JdbcTemplate jt2(){
    //    return new JdbcTemplate(ds2());
    //}




}

