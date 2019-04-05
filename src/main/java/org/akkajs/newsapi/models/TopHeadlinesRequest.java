package org.akkajs.newsapi.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.akkajs.newsapi.constants.Categories;
import org.akkajs.newsapi.constants.Countries;
import org.akkajs.newsapi.constants.Languages;

import lombok.Data;
import lombok.NonNull;

@Data
public class TopHeadlinesRequest {
  /// The keyword or phrase to search for. Boolean operators are supported.
  public String Q;
  /// If you want to restrict the results to specific sources, add their Ids here.
  /// You can find source Ids with the /sources endpoint or on newsapi.org.
  public List<String> Sources = new LinkedList();
  /// If you want to restrict the headlines to a specific news category, add these
  /// here.
  @NonNull
  public Optional<Categories> category = Optional.empty();
  /// The language to restrict articles to.
  @NonNull
  public Optional<Languages> Language = Optional.empty();
  /// The country of the source to restrict articles to.
  @NonNull
  public Optional<Countries> country = Optional.empty();
  /// Each request returns a fixed amount of results. Page through them by increasing this.
  public int page;
  /// Set the max number of results to retrieve per request. The max is 100.
  public int pageSize;
}
