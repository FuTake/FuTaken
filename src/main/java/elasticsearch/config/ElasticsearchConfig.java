package elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.server}")
    String serverList;
    @Value("${elasticsearch.user:}")
    String user;
    @Value("${elasticsearch.pass:}")
    String password;
    @Bean
    public RestHighLevelClient restHighLevelClient() throws Exception {
        return initRestHighLevelClient();
    }
    public RestHighLevelClient initRestHighLevelClient() throws Exception {
        log.info("initRestHighLevelClient server:{} user:{}", serverList);
        String[] elasticSearchServer = serverList.split(",");
        HttpHost httpHosts[] = new HttpHost[elasticSearchServer.length];

        for (int i=0;i<httpHosts.length;i++){
            String strServerEach = elasticSearchServer[i];
            String ipPortList[] = strServerEach.split(":");
            if (ipPortList.length==2){
                HttpHost httpHost = new HttpHost(ipPortList[0].trim(), Integer.valueOf(ipPortList[1].trim()), "http");
                httpHosts[i] = httpHost;
            }else{
                throw new Exception("elasticsearch.server config error");
            }
        }

        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts).setRequestConfigCallback(
                requestConfigBuilder->requestConfigBuilder.setConnectTimeout(300 * 1000) // 连接超时（默认为1秒）
                            .setSocketTimeout(300 * 1000).setConnectionRequestTimeout(300 * 1000)
               ).setMaxRetryTimeoutMillis(300 * 1000);
        if(!StringUtils.isEmpty(user)){
            //需要用户名和密码的认证, 详见官方文档： https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/_basic_authentication.html
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
            restClientBuilder.setHttpClientConfigCallback(
                    httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }

}