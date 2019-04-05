package org.akkajs.newsapi.models;

import org.akkajs.newsapi.constants.Statuses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = ArticlesResult.ArticlesResultBuilder.class)
@Builder(builderClassName = "ArticlesResultBuilder", toBuilder = true)
public class ArticlesResult {
  private Statuses status;
  private Error error;
  private int totalResults;
  private List<Article> articles;

  @JsonPOJOBuilder(withPrefix = "")
  public static class ArticlesResultBuilder {
  }
}