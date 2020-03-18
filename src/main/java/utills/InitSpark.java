package utills;

import org.apache.spark.sql.SparkSession;

public class InitSpark {
    public static SparkSession initSpark() {

        //  切换集群用户
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        System.setProperty("user.name", "hadoop");

        //  spark 初始化
        SparkSession spark = SparkSession.builder()
                .appName("SparkToPG")
                .master("yarn")
                .config("spark.sql.warehouse.dir", "/warehouse/tablespace/managed/hive")
                .config("hive.metastore.uris", "thrift://namenode.gaialab.ai:9083")
                .config("spark.debug.maxToStringFields", "300")
                .config("hive.execution.engine", "mr")
                .enableHiveSupport()
                .getOrCreate();


        return spark;


    }
}
