package common;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import utills.JDBCUtil;
import utills.PGProperties;

import java.util.Properties;

public class HiveToPG {
    private static String url = null;
    private static Properties pro = null;

    public static void ToPg(){

        SparkSession spark = utills.InitSpark.initSpark();
        url = JDBCUtil.getUrl();
        pro = PGProperties.getPro();

        String emp = "dataman.ods_org_emp";
        String dep = "dataman.ods_org_dep";
        String cust = "dataman.dwd_cust";
        String info = "dataman.dwd_base_info";


        String empsql = "select *,unix_timestamp() as data_version from ods_sftm_new.ods_org_emp";
        appendData(spark,empsql,url,emp,pro);

        // 2.查询hive数据  部门表
        String depsql = "select *,unix_timestamp() as data_version from ods_sftm_new.ods_org_dep";
        appendData(spark,depsql,url,dep,pro);

        // 3.查询 Hive经销商门店宽表
        String custsql = "select *,unix_timestamp() as data_version from dwd_sftm.dwd_cust";
        appendData(spark,custsql,url,cust,pro);

        // 4.查询 hive 部门、员工、门店、经销商宽表
        String infosql = "select *,unix_timestamp() as data_version from dwd_sftm.dwd_base_info";
        appendData(spark,infosql,url,info,pro);

        spark.close();

    }

    public static void appendData(SparkSession spark, String sql, String url, String name, Properties pro) {
        Dataset<Row> DF = spark.sql(sql);
        DF.write().mode("append").jdbc(url,name,pro);

    }

}
