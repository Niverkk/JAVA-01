package info.niverkk.course10.work06;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * @author JKXAING on 2021/2/20
 */
public class HikariCPTest {


    /**
     * 配置数据源，从数据源获取连接
     * @return
     * @throws Exception
     */
    public Connection getConn() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("******");
        dataSource.setJdbcUrl("jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/vhr");

        Connection connection = dataSource.getConnection();
        return connection;
    }


    @Test
    public void insert() throws Exception {
        Connection connection = getConn();
        // 使用conn 创建statement
        PreparedStatement statement = connection.prepareStatement("insert into role(id,name) values(? , ?)");

        // 设置参数
        statement.setInt(1, 666);
        statement.setString(2, "geekps");
        // 执行sql语句
        int i = statement.executeUpdate();
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }

    @Test
    public void query() throws Exception {
        Connection connection = getConn();

        // 使用conn 创建statement
        PreparedStatement statement = connection.prepareStatement("select * from role where id = ?");

        // 设置参数
        statement.setInt(1, 666);

        //4.执行sql语句
        ResultSet resultSet = statement.executeQuery();

        //5.处理结果集
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"\t"
                    + resultSet.getString(2) +"\t"
                    + resultSet.getObject(3, String.class));
        }

        //关闭资源，可使用 java7 的 try-resources方式
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void update() throws Exception {
        Connection connection = getConn();

        // 使用conn 创建statement
        PreparedStatement statement = connection.prepareStatement("update role set name=? where id=?");

        // 设置参数
        statement.setInt(2, 999);
        statement.setString(1, "geekps2");

        //4.执行sql语句,
        int i = statement.executeUpdate();
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }

    @Test
    public void delete() throws Exception {
        Connection connection = getConn();

        // 使用conn 创建statement
        PreparedStatement statement = connection.prepareStatement("delete from role where id = ?");

        // 设置参数
        statement.setInt(1, 999);

        statement.addBatch();
        statement.executeBatch();

        //4.执行sql语句,
        int i = statement.executeUpdate();
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }

    @Test
    public void batchUpdate() throws Exception {
        Connection conn = getConn();
        //关闭自当提交，开启事务控制
        conn.setAutoCommit(false);
        //指定 rs  可滚动 并 可更新
        PreparedStatement ps = conn.prepareStatement("insert into rolecopy values(null,?)"
                ,ResultSet.TYPE_SCROLL_INSENSITIVE
                ,ResultSet.CONCUR_UPDATABLE);

        for (int i = 0; i < 6; i++) {
            ps.setString(1, "batch"+i);
            //ps.executeUpdate();
            ps.addBatch();
        }
        int[] ints = ps.executeBatch();
        System.out.println(Arrays.toString(ints));
        //提交事务
        conn.commit();
    }

    @Test
    public void testTc() throws Exception {
        Connection conn = getConn();
        //关闭自当提交，开启事务控制
        conn.setAutoCommit(false);
        //指定 rs  可滚动 并 可更新
        PreparedStatement ps = conn.prepareStatement("insert into rolecopy values(null,?)"
                ,ResultSet.TYPE_SCROLL_INSENSITIVE
                ,ResultSet.CONCUR_UPDATABLE);

        for (int i = 0; i < 6; i++) {
            ps.setString(1, "trans"+i);
            ps.executeUpdate();
            //回滚3及之前的 数据
            if(i == 3){
                conn.rollback();
            }
        }
        //提交事务
        conn.commit();
    }



}
