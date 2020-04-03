package utills;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DataSourceUtil {
    private static DataSource ds ;
    private static Connection con ;

    public static Connection connactionByDataSource(){

        try {
            //1.加载配置文件
            Properties properties = new Properties();
            properties.load(DataSourceUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));

            //2.获取datasource
            ds = DruidDataSourceFactory.createDataSource(properties);

            //3.建立连接
            con = ds.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;

    }
}
