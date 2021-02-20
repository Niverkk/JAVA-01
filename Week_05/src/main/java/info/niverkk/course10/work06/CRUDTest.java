package info.niverkk.course10.work06;

import org.junit.Test;

import java.sql.*;

/**
 *
 *  1.executeQuery  只能执行查询语句，select
 *  2.executeUpdate 可以执行DML语句，insert，update，delete等
 *
 * @author JKXAING on 2021/2/20
 */
public class CRUDTest {

    public static void main(String[] args) throws Exception {
        //查询语句
        //query();

        //executeUpdate,ddl
        //executeUpdate();

        //executeUpdate,dml
        //executeUpdate2();

        //execute

        //删除表的ddl
        //execute("drop table if exists rolecopy");
        ////创建表的ddl
        //execute("create table rolecopy (" +
        //        "id int primary key," +
        //        "name varchar(255)" +
        //        ")");
        //
        //execute("insert into rolecopy " +
        //        "select id,name  from role where id <10");
        //execute("select * from role");
    }

    public void execute(String sql) throws Exception {
        Connection connection = getConn();
        //3.使用conn 创建statement
        Statement statement = connection.createStatement();

        //4.执行sql语句
        //是否返回了resultset对象
        boolean ishasRs = statement.execute(sql);

        if (ishasRs) {
            ResultSet rs = statement.getResultSet();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while(rs.next()){
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i)+"\t");
                }
                System.out.println();
            }
        }else{
            System.out.println("受影响的行数"+statement.getUpdateCount());
        }

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();

    }

    @Test
    public void insert() throws Exception {
        Connection connection = getConn();
        //3.使用conn 创建statement
        Statement statement = connection.createStatement();

        //4.执行sql语句,
        String sql = " insert into role(id,name) values(888, 'geek') ";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }

    @Test
    public void query() throws Exception {
        Connection connection = getConn();

        //3.使用conn 创建statement
        Statement statement = connection.createStatement();

        //4.执行sql语句
        String sql = "select * from role where id = 888";
        ResultSet resultSet = statement.executeQuery(sql);

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
        //3.使用conn 创建statement
        Statement statement = connection.createStatement();

        //4.执行sql语句,
        String sql = "update role set name='geek2' where id = 888";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }

    @Test
    public void delete() throws Exception {
        Connection connection = getConn();
        //3.使用conn 创建statement
        Statement statement = connection.createStatement();

        //4.执行sql语句,
        String sql = "delete from role where id = 888";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        //关闭资源，可使用 java7 的 try-resources方式
        statement.close();
        connection.close();
    }


    public Connection getConn() throws Exception {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql://gz-cynosdbmysql-grp-0ft8922d.sql.tencentcdb.com:20820/vhr";
        String username = "root";
        String pwd = "******";
        Connection connection = DriverManager.getConnection(url, username, pwd);

        return connection;
    }


}
