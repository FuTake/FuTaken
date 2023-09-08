package TestController.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhg
 * @date 2023/9/7
 */
@RestController("/template")
@Slf4j
public class RestTemplateController {

    @Autowired
    private RestTemplate loadBalance;

    private static final String unicomPrometheus = "http://10.177.56.161:9090";

    private static final String URL = "/api/v1/query?query={query}";
    //查询行云实例的cpu配额
    private static final String CIRRO_CPU = "count(last_over_time(node_cpu_seconds_total{job=\"%s\",prov=\"%d\",mode=\"idle\"}[30m]))\n";

//    @PostConstruct
    public void test(){
        String query = CIRRO_CPU.replace("%s", "应用Ⅰ域集群").replace("%d", "DC_038");
        System.out.println(query);
        JSONArray result = new JSONArray();
        String requestUrl = unicomPrometheus + URL;
        System.out.println(requestUrl);
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("encode query{} error", query);
        }
        Map<String, String> map = new HashMap<>();
        map.put("query", query);
        // 查看调用日志，进里面打断点
        String response = loadBalance.getForObject(requestUrl, String.class, map);
    }
}
