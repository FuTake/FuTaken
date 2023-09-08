package TestController.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhg
 * @date 2023/9/8
 */
@Component
public class PrometheusUtils {

    @Autowired
    private RestTemplate client;

    //查询集群节点数入参
    private static final String CLUSTER_NODE = "count(last_over_time(node_cpu_seconds_total{cpu=\"0\",job=\"%s\",mode=\"idle\"}[30m]))";
    //查询集群CPU数入参
    private static final String CLUSTER_CPU = "count(last_over_time(node_cpu_seconds_total{job=\"%s\",mode=\"idle\"}[30m]))";
    //查询集群内存数入参
    private static final String CLUSTER_MEMORY = "sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\"}[30m]))";
    // Hadoop的cpu、内存
    private static final String YARN_CLUSTER = "last_over_time(yarn_cluster_noquota{CLUSTER_NAME_BAK=\"%s\"}[30m])";
    //Hadoop存储总量
    private static final String NN_METRICS = "last_over_time(nn_metrics{CLUSTER_NAME_BAK=\"%s\"}[30m])";

    //查询队列的存储使用量
    private static final String STORAGE = "sum(last_over_time(linux_hdfs_user_metrics_noquota{user_name=\"/tenants/%d\",CLUSTER_NAME_BAK=\"%s\",title=\"CONTENT_SIZE\"}[24h]))";

    //查询队列的存储总量
    private static final String STORAGE_TOTAL = "sum(last_over_time(linux_hdfs_user_metrics_noquota{user_name=\"/tenants/%d\",CLUSTER_NAME_BAK=\"%s\",title=\"SPACE_QUOTA_T\"}[24h]))";

    //
    //查询实例cpu使用量
    private static final String YARN_QU = "last_over_time(yarnvCores_noquota{CLUSTER_NAME_BAK=\"%s\",title=\"usedvCores\"}[30m])";

    //查询实例cpu总量
    private static final String YARN_QU_TOTAL = "last_over_time(yarnvCores_noquota{CLUSTER_NAME_BAK=\"%s\",title=\"maxvCores\"}[30m])";

    //查询Yarn队列内存使用量
    private static final String MEMORY = "last_over_time(yarnmemory_noquota{CLUSTER_NAME_BAK=\"%s\",title=\"usedmemory\"}[30m])";

    //查询yarn队列内存总量 (单位：MB 需要换算到 TB)
    private static final String MEMORY_TOTAL = "last_over_time(yarnmemory_noquota{CLUSTER_NAME_BAK=\"%s\",title=\"maxmemory\"}[30m])";

    //hBase集群节点数
    public static final String HBASE_CLUSTER = "last_over_time(hbase_metrics{CLUSTER_NAME_BAK=\"%s\"}[30m])";
    //hBase集群cpu已分配
    public static final String HBASE_CPU_USED = "sum(hbase_metrics{title=\"rs_region_server_num\",CLUSTER_NAME_BAK=\"%s\"})";
    //hBase集群内存已分配
    public static final String HBASE_MEMORY_TOTAL = "sum(hbase_metrics{title=\"rs_memory_max_GB\",CLUSTER_NAME_BAK=\"%s\"})";
    //hBase集群存储总量
    public static final String HBASE_STORAGE_TOTAL = "linux_hdfs_user_metrics{user_name=\"/hbase\",title=\"SPACE_QUOTA_G\",CLUSTER_NAME_BAK=\"%s\"}[1h]";
    //hBase集群存储已使用
    public static final String HBASE_STORAGE_USED = "linux_hdfs_user_metrics{title=\"SPACE_QUOTA_G\",CLUSTER_NAME_BAK=\"%s\",user_name=~\"/hbase/data/.*\"}[1h]";
    //hbase集群RSGroup的cpu、内存
    private static final String HBASE_RSGROUP_TENANT = "hbase_metrics{CLUSTER_NAME_BAK=\"%s\",tenant=\"%d\"}";
    //hbase集群RSGroup的存储
    private static final String HBASE_RSGROUP_QUOTA = "linux_hdfs_user_metrics{title= \"USE_SPACE_QUOTA_PERCENT\",CLUSTER_NAME_BAK=\"%s\",user_name=~\"/hbase/data/%d\"}[1h]";

    //行云
    //查询行云实例的存储配额
    private static final String CIRRO_STORAGE = "last_over_time(cirro_metrics_noquota{CLUSTER_NAME_BAK=\"%s\",prov=\"%d\",title=\"SPACE_QUOTA_G\"}[6h])\n";
    //查询行云实例的使用量
    private static final String CIRRO_STORAGE_USE = "last_over_time(cirro_metrics_noquota{CLUSTER_NAME_BAK=\"%s\",prov=\"%d\",title=\"CONTENT_SIZE\"}[6h])\n";
    //查询行云集群的使用量
    private static final String CIRRO_CLUSTER_STORAGE_USE = "last_over_time(nn_metrics{title=\"DFS_Used_GB\",CLUSTER_NAME_BAK=\"%s\"}[6h])";
    //查询行云集群的存储配额
    private static final String CIRRO_CLUSTER_STORAGE = "last_over_time(cirro_metrics_noquota{CLUSTER_NAME_BAK=\"%s\",title=\"SPACE_QUOTA_G\"}[6h])\n";
    //查询行云用户的使用量
    private static final String CIRRO_USER_STORAGE_USE = "last_over_time(cirro_metrics_noquota{CLUSTER_NAME_BAK=\"%s\",prov=\"%d\",tenant=\"%t\",title=\"CONTENT_SIZE\"}[6h])\n";

    private static final String CIRRO_INSTANCE_USER_AND_STORAGE = "last_over_time(cirro_metrics_noquota{CLUSTER_NAME_BAK=\"%s\",prov=\"%d\",title=\"SPACE_QUOTA_G\"}[1h])";
    //查询行云实例的cpu配额
    private static final String CIRRO_CPU = "count(last_over_time(node_cpu_seconds_total{job=\"%s\",prov=\"%d\",mode=\"idle\"}[30m]))\n";
    //查询行云实例的cpu使用率
    private static final String CIRRO_USE_PERCENT = "avg(1-avg(rate(node_cpu_seconds_total{job=\"%s\",prov=\"%d\",mode=\"idle\"}[30m]))by(instance))*100\n";
    //查询行云集群的cpu配额
    private static final String CIRRO_CLUSTER_CPU = "count(last_over_time(node_cpu_seconds_total{job=\"%s\",mode=\"idle\"}[30m]))\n";
    //查询行云集群的cpu使用率
    private static final String CIRRO_CLUSTER_CPU_USE_PERCENT = "avg(1-avg(rate(node_cpu_seconds_total{job=\"%s\",mode=\"idle\"}[30m]))by(instance))*100\n";
    //查询行云内存配额
    private static final String CIRRO_MEMORY_TOTAL = "sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\",prov=\"%d\"}[30m]))\n";
    //查询行云内存使用量
    private static final String CIRRO_MEMORY_PERCENT = "(sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\",prov=\"%d\"}[30m])-last_over_time(node_memory_MemAvailable_bytes{job=\"%s\",prov=\"%d\"}[30m]))/sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\",prov=\"%d\"}[30m])))*100\n";
    //查询行云集群内存配额
    private static final String CIRRO_CLUSTER_MEMORY_TOTAL = "sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\"}[30m]))\n";
    //查询行云内存使用量
    private static final String CIRRO_CLUSTER_MEMORY_PERCENT = "(sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\"}[30m])-last_over_time(node_memory_MemAvailable_bytes{job=\"%s\"}[30m]))/sum(last_over_time(node_memory_MemTotal_bytes{job=\"%s\"}[30m])))*100\n";


    private static final String CIRRO_CLUSTER_NODE_NUM = "last_over_time(node_cpu_seconds_total{cpu=\"0\",job=\"%s\",mode=\"idle\"}[30m])";

    private static final String CIRRO_CLUSTER_NODE_USE_NUM = "count(last_over_time(node_cpu_seconds_total{cpu=\"0\",job=\"%s\",mode=\"idle\",prov=\"%d\"}[10m]))";

    //查询行云所有实例
    private static final String CIRRO_INSTANCE_LISTS = "last_over_time(cirro_metrics_noquota{title=\"CONTENT_SIZE\"}[6h])";

    private static final String URL = "/api/v1/query?query={query}";

    private static final String unicomPrometheus = "http://unicom-prometheus";

    public void callPrometheus(){
        // 调用普罗的api
        String requestUrl = unicomPrometheus + URL;
        Map<String, String> map = new HashMap<>();
        map.put("query", CIRRO_INSTANCE_LISTS);
        String response = client.getForObject(requestUrl, String.class, map);
    }
}
