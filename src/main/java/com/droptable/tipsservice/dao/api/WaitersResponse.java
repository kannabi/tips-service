package com.droptable.tipsservice.dao.api;

import com.droptable.tipsservice.dao.db.Waiter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WaitersResponse {

    private List<Waiter> waiters;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_page")
    private int totalPages;

    public WaitersResponse(List<Waiter> waiters, int currentPage, int totalPages) {
        this.waiters = waiters;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public WaitersResponse() {
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
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
