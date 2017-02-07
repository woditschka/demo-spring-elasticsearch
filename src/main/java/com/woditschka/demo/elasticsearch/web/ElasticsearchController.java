package com.woditschka.demo.elasticsearch.web;

import com.woditschka.demo.elasticsearch.model.TotalResult;
import com.woditschka.demo.elasticsearch.repository.ElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ElasticsearchController {

  private final ElasticRepository elasticRepository;

  @Autowired
  public ElasticsearchController(ElasticRepository elasticRepository) {
    this.elasticRepository = elasticRepository;
  }

  @ResponseBody
  @RequestMapping("/total")
  public TotalResult test() throws IOException {

    Integer total = elasticRepository.getTotal();

    return new TotalResult(total);
  }
}
