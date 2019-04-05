package org.akkajs.newsapi.models;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.akkajs.newsapi.constants.Languages;
import org.akkajs.newsapi.constants.SortBys;

import lombok.Data;
import lombok.NonNull;

/// Params for making a request to the /everything endpoint.
@Data
public class EverythingRequest {
    /// The keyword or phrase to search for. Boolean operators are supported.
    private String q;
    /// If you want to restrict the search to specific sources, add their Ids here.
    /// You can find source Ids with the /sources endpoint or on newsapi.org.
    @NonNull
    private List<String> sources = new LinkedList();
    /// If you want to restrict the search to specific web domains, add these here.
    /// Example: nytimes.com.
    @NonNull
    private List<String> domains = new LinkedList();
    /// The earliest date to retrieve articles from. Note that how far back you can
    /// go is constrained by your plan type. See newsapi.org/pricing for plan
    /// details.
    @NonNull
    private Optional<LocalDateTime> from = Optional.empty();
    /// The latest date to retrieve articles from.
    @NonNull
    private Optional<LocalDateTime> to = Optional.empty();
    /// The language to restrict articles to.
    @NonNull
    private Optional<Languages> language = Optional.empty();
    /// How should the results be sorted? Relevancy = articles relevant to the Q
    /// param come first. PublishedAt = most recent articles come first. Publisher =
    /// popular publishers come first.
    @NonNull
    private Optional<SortBys> sortBy = Optional.empty();
    /// Each request returns a fixed amount of results. Page through them by
    /// increasing this.
    private int page;
    /// Set the max number of results to retrieve per request. The max is 100.
    private int pageSize;
}
