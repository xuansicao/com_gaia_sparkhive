package APP;

import common.HiveToPG;
import common.PGTruncate;
import utills.DataSourceUtil;
import utills.JDBCUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class SparkToPG {


    static Connection connection = null;
    public static void main(String[] args) {


        try {
            //方式一：
            connection = JDBCUtil.getConnection();

            //方式二：
//            con = DataSourceUtil.connactionByDataSource();
//            PGTruncate.trunData(con,"dataman.test1");


            //1.清空PG表数据
            PGTruncate.trunData(connection, "dataman.ods_org_emp");
            PGTruncate.trunData(connection, "dataman.ods_org_dep");
            PGTruncate.trunData(connection, "dataman.dwd_base_info");
            PGTruncate.trunData(connection, "dataman.dwd_cust");




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //JDBCUtil.closeConnection(null,null,con);
        }



        //***********************每天全量导入pg********************************
        //1.部门表  2.员工表  3.门店经销商融合表   4.部门、员工、门店、经销商融合表
        HiveToPG.ToPg();


    }
}
