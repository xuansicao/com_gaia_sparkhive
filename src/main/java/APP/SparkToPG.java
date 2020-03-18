package APP;

import common.HiveToPG;
import common.PGTruncate;
import jodd.util.CsvUtil;
import org.apache.spark.sql.SparkSession;
import utills.JDBCUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SparkToPG {
    public static void main(String[] args) {

        try {
            Connection connection = JDBCUtil.getConnection();
            //1.清空PG表数据
            PGTruncate.trunData(connection, "dataman.ods_org_emp");
            PGTruncate.trunData(connection, "dataman.ods_org_dep");
            PGTruncate.trunData(connection, "dataman.dwd_base_info");
            PGTruncate.trunData(connection, "dataman.dwd_cust");

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //***********************每天全量导入pg********************************
        //1.部门表  2.员工表  3.门店经销商融合表   4.部门、员工、门店、经销商融合表
        HiveToPG.ToPg();


    }
}
