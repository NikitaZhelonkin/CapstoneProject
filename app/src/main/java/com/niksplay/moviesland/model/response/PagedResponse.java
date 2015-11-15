package com.niksplay.moviesland.model.response;

import java.util.List;

/**
 * Created by nikita on 15.11.15.
 */
public class PagedResponse<T> {

    private int page;

    private List<T> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
