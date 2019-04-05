package org.akkajs.newsapi;

import java.util.Optional;

import org.akkajs.newsapi.constants.Statuses;
import org.akkajs.newsapi.constants.Languages;
import org.akkajs.newsapi.models.ArticlesResult;
import org.akkajs.newsapi.models.EverythingRequest;
import org.akkajs.newsapi.models.SourcesRequest;
import org.akkajs.newsapi.models.SourcesResponse;
import org.akkajs.newsapi.models.TopHeadlinesRequest;

public class Test {
  public static void main(String[] args) throws Exception {
    NewsApiClient apiClient = new NewsApiClient(System.getenv("NEWS_API_TOKEN"));

    EverythingRequest everythingReq = new EverythingRequest();
    everythingReq.setQ("pizza");
    everythingReq.setLanguage(Optional.of(Languages.en));

    ArticlesResult everythingRes = apiClient.getEverything(everythingReq).get();

    if (everythingRes.getStatus() == Statuses.error) {
      System.err.println("Error:");
      System.err.println(everythingRes.getError().getCode());
      System.err.println(everythingRes.getError().getMessage());
    } else {
      System.out.println("Everything:");
      
      System.out.println(everythingRes.getTotalResults());
      System.out.println(everythingRes.getArticles().size());
    }

    TopHeadlinesRequest thReq = new TopHeadlinesRequest();
    thReq.setQ("pasta");
    thReq.setLanguage(Optional.of(Languages.en));

    ArticlesResult thRes = apiClient.getEverything(everythingReq).get();

    if (thRes.getStatus() == Statuses.error) {
      System.err.println("Error:");
      System.err.println(thRes.getError().getCode());
      System.err.println(thRes.getError().getMessage());
    } else {
      System.out.println("Top headlines:");
      
      System.out.println(thRes.getTotalResults());
      System.out.println(thRes.getArticles().size());
    }

    SourcesRequest sReq = new SourcesRequest();

    SourcesResponse sRes = apiClient.getSources(sReq).get();

    if (sRes.getStatus() == Statuses.error) {
      System.err.println("Error");
    } else {
      System.out.println("Sources:");

      System.out.println(sRes.getSources());
    }

    System.exit(0);
  }
}