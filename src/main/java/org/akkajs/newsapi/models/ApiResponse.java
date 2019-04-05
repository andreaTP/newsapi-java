package org.akkajs.newsapi.models;

import org.akkajs.newsapi.constants.Statuses;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import org.akkajs.newsapi.constants.ErrorCodes;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonDeserialize(builder = ApiResponse.ApiResponseBuilder.class)
@Builder(builderClassName = "ApiResponseBuilder", toBuilder = true)
public class ApiResponse {
    private Statuses status;
    @Builder.Default
    private Optional<ErrorCodes> code = Optional.empty();
    private String message;
    @Builder.Default
    private List<Article> articles = new LinkedList();
    private int totalResults;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ApiResponseBuilder {
    }
}

