package info.niverkk.course13.work02;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 插入 100 万订单模拟数据，测试不同方式的插入效率
 *
 * @author JKXAING on 2021/3/6
 */
public class InsertLargeDataTest {

    /**
     * 1.批量更新
     * 2.关闭自动提交事务
     * 3.开启rewriteBatchedStatements，最关键;
     * 不开启时，执行速度很慢，没等下去。
     *
     * @throws Exception
     */
    @Test
    public void batchUpdate() throws Exception {
        Connection conn = getConn();
        // 关闭自当提交，开启事务控制
        conn.setAutoCommit(false);
        // 35s左右
        String sql = " INSERT INTO `j_order` (`harvest_info`, `totalprice`, `status`, `payment_time`, `ship_time`, `deal_time`) VALUES ('订单收货信息1', ?, '1', '2021-03-06 20:51:27', '2021-03-06 20:51:29', '2021-03-06 20:51:34')\n";

        // 不使用数据库默认值，自己设值速度下降了--57s左右
        String sql2 = " INSERT INTO `j_order` (`harvest_info`, `totalprice`, `status`, `payment_time`, `ship_time`, `deal_time`, `created_time`, `created_by`, `updaed_time`, `update_by`) VALUES ('订单收货信息1', ? , '1', '2021-03-06 20:51:27', '2021-03-06 20:51:29', '2021-03-06 20:51:34', '2021-03-06 23:11:59', 'root', '2021-03-06 23:12:08', 'root')";

        PreparedStatement ps = conn.prepareStatement(sql2);

        long start = System.currentTimeMillis();
        int count = 0;

        for (int i = 1; i <= 1000000; i++) {
            ps.setInt(1, i);
            //攒sql语句
            ps.addBatch();
            if(i % 10000 == 0){
                //执行批量sql语句
                int[] ints = ps.executeBatch();
                count = count + ints.length;
                //清空缓存
                ps.clearBatch();
            }
        }
        //ps.executeBatch();
        System.out.println("===========");
        //提交事务
        conn.commit();

        long end = System.currentTimeMillis();
        System.out.println("总耗时(s)：" + (end - start) / 1000);
        System.out.println("执行成功数量：" + count);

        //关闭资源，可使用 java7 的 try-resources方式
        ps.close();
        conn.close();
    }


    public Connection getConn() throws Exception {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/jeek?rewriteBatchedStatements=true";
        String username = "root";
        String pwd = "";
        Connection connection = DriverManager.getConnection(url, username, pwd);

        return connection;
    }
}
