package org.akkajs.newsapi.models;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.akkajs.newsapi.constants.Categories;
import org.akkajs.newsapi.constants.Countries;
import org.akkajs.newsapi.constants.Languages;
import org.akkajs.newsapi.constants.SortBys;

import lombok.Data;
import lombok.NonNull;

/// Params for making a request to the /everything endpoint.
@Data
public class SourcesRequest {
  @NonNull
  public Optional<Categories> category = Optional.empty();
  /// The language to restrict articles to.
  @NonNull
  public Optional<Languages> Language = Optional.empty();
  /// The country of the source to restrict articles to.
  @NonNull
  public Optional<Countries> country = Optional.empty();
}
