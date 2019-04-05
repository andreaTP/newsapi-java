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
@JsonDeserialize(builder = SourcesResponse.SourcesResponseBuilder.class)
@Builder(builderClassName = "SourcesResponseBuilder", toBuilder = true)
public class SourcesResponse {
  private Statuses status;
  private List<Source> sources;

  @JsonPOJOBuilder(withPrefix = "")
  public static class SourcesResponseBuilder {
  }
}