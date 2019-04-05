package org.akkajs.newsapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pivovarit.function.ThrowingFunction;

import org.akkajs.newsapi.constants.Statuses;
import org.akkajs.newsapi.models.ApiResponse;
import org.akkajs.newsapi.models.ArticlesResult;
import org.akkajs.newsapi.models.Error;
import org.akkajs.newsapi.models.EverythingRequest;
import org.akkajs.newsapi.models.SourcesRequest;
import org.akkajs.newsapi.models.SourcesResponse;
import org.akkajs.newsapi.models.TopHeadlinesRequest;

import kong.unirest.Unirest;

public class NewsApiClient {

  static String BASE_URL = "https://newsapi.org/v2/";

  private String apiKey;

  private ObjectMapper objectMapper;

  public NewsApiClient(String apiKey) {
    this.apiKey = apiKey;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new Jdk8Module());
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  public CompletableFuture<ArticlesResult> getEverything(EverythingRequest request) {
    Map<String, Object> params = new HashMap();

    Optional.ofNullable(request.getQ()).ifPresent(q -> params.put("q", q.trim()));
    Optional.ofNullable(request.getSources()).ifPresent(s -> {
      if (s.size() > 0)
        params.put("sources", s);
    });
    Optional.ofNullable(request.getDomains()).ifPresent(d -> {
      if (d.size() > 0)
        params.put("domains", d);
    });
    request.getFrom().ifPresent(f -> params.put("from", f));
    request.getTo().ifPresent(t -> params.put("to", t));
    request.getLanguage().ifPresent(lang -> params.put("language", lang));
    request.getSortBy().ifPresent(s -> params.put("sortBy", s));

    if (request.getPage() > 1)
      params.put("page", request.getPage());

    if (request.getPageSize() > 0)
      params.put("pageSize", request.getPageSize());

    CompletableFuture<ApiResponse> res = makeRequest("everything", params);

    return res.thenApply(req -> {
      ArticlesResult ar = new ArticlesResult();
      ar.setStatus(req.getStatus());
      if (req.getStatus() != Statuses.ok) {
        ar.setError(new Error(req.getCode().get(), req.getMessage()));
      } else {
        ar.setTotalResults(req.getTotalResults());
        ar.setArticles(req.getArticles());
      }
      return ar;
    });
  }

  public CompletableFuture<ArticlesResult> getTopHeadlines(TopHeadlinesRequest request) {
    Map<String, Object> params = new HashMap();

    Optional.ofNullable(request.getQ()).ifPresent(q -> params.put("q", q.trim()));
    Optional.ofNullable(request.getSources()).ifPresent(s -> {
      if (s.size() > 0)
        params.put("sources", s);
    });
    request.getCategory().ifPresent(c -> params.put("category", c));
    request.getLanguage().ifPresent(lang -> params.put("language", lang));
    request.getCountry().ifPresent(c -> params.put("country", c));

    if (request.getPage() > 1)
      params.put("page", request.getPage());

    if (request.getPageSize() > 0)
      params.put("pageSize", request.getPageSize());

    CompletableFuture<ApiResponse> res = makeRequest("top-headlines", params);

    return res.thenApply(req -> {
      ArticlesResult ar = new ArticlesResult();
      ar.setStatus(req.getStatus());
      if (req.getStatus() != Statuses.ok) {
        ar.setError(new Error(req.getCode().get(), req.getMessage()));
      } else {
        ar.setTotalResults(req.getTotalResults());
        ar.setArticles(req.getArticles());
      }
      return ar;
    });
  }

  public CompletableFuture<SourcesResponse> getSources(SourcesRequest request) {
    Map<String, Object> params = new HashMap();

    request.getCategory().ifPresent(c -> params.put("category", c));
    request.getLanguage().ifPresent(lang -> params.put("language", lang));
    request.getCountry().ifPresent(c -> params.put("country", c));

    CompletableFuture<SourcesResponse> jsonResponse = Unirest.get(BASE_URL + "sources")
        .header("accept", "application/json").queryString(params).header("x-api-key", apiKey).asStringAsync()
        .thenApply(ThrowingFunction.unchecked((resp -> {
          return objectMapper.readValue(resp.getBody(), SourcesResponse.class);
        })));

    return jsonResponse;
  }

  public CompletableFuture<ApiResponse> makeRequest(String endpoint, Map<String, Object> params) {
    CompletableFuture<ApiResponse> jsonResponse = Unirest.get(BASE_URL + endpoint).header("accept", "application/json")
        .queryString(params).header("x-api-key", apiKey).asStringAsync().thenApply(ThrowingFunction.unchecked((resp -> {
          return objectMapper.readValue(resp.getBody(), ApiResponse.class);
        })));

    return jsonResponse;
  }
}
