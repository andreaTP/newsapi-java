package org.akkajs.newsapi.models;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Article.ArticleBuilder.class)
@Builder(builderClassName = "ArticleBuilder", toBuilder = true)
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Optional<LocalDateTime> publishedAt;
    private String content;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ArticleBuilder {
    }
}

