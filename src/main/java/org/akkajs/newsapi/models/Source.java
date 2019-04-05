package org.akkajs.newsapi.models;

import java.util.Optional;

import org.akkajs.newsapi.constants.Categories;
import org.akkajs.newsapi.constants.Countries;
import org.akkajs.newsapi.constants.Languages;

import lombok.Data;

@Data
public class Source {
  public String id;
  public String name;
  private String description;
  private String url;
  // these are intended to be enums but the API returns unmatched values
  private String category;
  private String language;
  private String country;
}
