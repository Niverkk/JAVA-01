package info.niverkk.course14.work03;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@SpringBootApplication
public class SplitByShardingsphereApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SplitByShardingsphereApplication.class, args);
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `j_order` (`harvest_info`, `totalprice`, `status`, `payment_time`, `ship_time`, `deal_time`) VALUES ('订单收货信息1', 100, '1', '2021-03-06 20:51:27', '2021-03-06 20:51:29', '2021-03-06 20:51:34')\n");
             PreparedStatement statement2 = connection.prepareStatement("select * from `j_order` limit 1");
        ) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement1.setTimestamp(1, timestamp);
            statement1.setTimestamp(2, timestamp);
            statement1.execute();
            rs = statement2.executeQuery();
            // 写完读走的主库
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `order`");
        ) {
            rs = statement.executeQuery();
            // 重新读走的从库
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    public DataSource dataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jjdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/jeek?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("");
        dataSourceMap.put("master_ds", dataSource1);
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setJdbcUrl("jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/jeek2?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("");
        dataSourceMap.put("replica_ds", dataSource2);
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //配置读写分离规则
        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "master_ds", Arrays.asList("replica_ds"), "load_balancer"));
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);
        //创建DS
        try {
            return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Arrays.asList(ruleConfiguration), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
