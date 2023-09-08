package TestController.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author zhg
 * @date 2023/9/7
 */

public class HttpUtils {
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @param : url
     * @description: post请求 get
     */
    public String sendGetRequest(String url) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(headers);//请求体，包括请求数据 body 和 请求头 headers
        ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return strbody.getBody();
    }

    /**
     * @param : url
     * @param : data
     * @description: post请求 json
     */
    public String sendPostRequest(String url, JSONObject data) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(data, headers);//请求体，包括请求数据 body 和 请求头 headers
        ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return strbody.getBody();
    }

    /**
     * @param : url
     * @param : data
     * @description: post请求 json
     */
    public String sendPostRequest(String url, String data) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(data, headers);//请求体，包括请求数据 body 和 请求头 headers
        ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return strbody.getBody();
    }
}

