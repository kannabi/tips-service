package com.droptable.tipsservice.dao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_pages")
    private int totalPages;

    public Meta(int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public Meta() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
