package nacostest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhg
 * @date 2023/8/16
 */
@RestController
@RequestMapping("/tomcat")
@Slf4j
// 刷新远程配置使用
@RefreshScope
public class TestController {

    @Value("${nacosTest.param1:nothing}")
    private String param1;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @PostConstruct
    public void init(){
      log.info("param1:{}", param1);
    }

    @RequestMapping("test")
    public String test(String param){
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.join("_", param1, param, timeStr);
    }

    /**
     * nacos中注册了服务后，即可使用 服务名 对其接口进行调用，遇到UnknownHostException是因为RestTemplate的创建没有使用@LoadBalanced注解
     * 被调用的服务不需要这个配置
     * @return
     */
    @RequestMapping("call")
    public String call(){
        log.info("call start");
        ResponseEntity<String> result = restTemplate.getForEntity("http://nacosTest2-zhg/tomcat/test?param=测试调用nacostest2的接口", String.class);
        return result.getBody();
    }

    @RequestMapping("discoveryTest")
    public void discoveryTest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("nacosTest2-zhg");
        for (ServiceInstance instance : instances) {
            URI uri = instance.getUri();
            log.info("discoveryTest instance.uri=" + uri);
            ResponseEntity<String> result = restTemplate.getForEntity(uri + "/tomcat/test?param=测试调用nacostest2的接口", String.class);
            log.info(result.getBody());
        }
    }
}
