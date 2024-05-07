package elasticsearch.controller;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private RestHighLevelClient client;

    @GetMapping("/getContext")
    public Object getContext() throws IOException {

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(1);
        SearchRequest searchRequest = new SearchRequest("monitor_meta_syn_log");
        searchRequest.source(builder);
        return client.search(searchRequest);
    }
}
