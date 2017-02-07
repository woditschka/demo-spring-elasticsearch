package com.woditschka.demo.elasticsearch.model;

public class TotalResult {
  private Integer total;

  public TotalResult(Integer total) {
    this.total = total;
  }

  public Integer getTotal() {
    return total;
  }

  @Override
  public String toString() {
    return "TotalResult{" +
        "total='" + total + '\'' +
        '}';
  }
}
