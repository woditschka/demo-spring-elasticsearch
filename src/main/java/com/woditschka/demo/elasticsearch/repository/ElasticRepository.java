package com.woditschka.demo.elasticsearch.repository;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.joda.time.DateTime.now;

@Component
public class ElasticRepository {

  private final JestClient jestClient;

  @Autowired
  public ElasticRepository(JestClient jestClient) {
    this.jestClient = jestClient;
  }

  public Integer getTotal() throws IOException {

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    searchSourceBuilder.query(boolQuery()
        .must(wildcardQuery("query_string", "*"))
        .must(termQuery("field", "value"))
        .filter(rangeQuery("timestamp")
            .gt(now().minusMinutes(120).toDate())
            .lt(now().toDate())));

    Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex("logstash*")
        .build();

    SearchResult result = jestClient.execute(search);

    return result.getTotal();
  }
}
