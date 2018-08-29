package com.droptable.tipsservice.dao.api;

import com.droptable.tipsservice.dao.db.Waiter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WaitersResponse {

    @JsonProperty("stuffs")
    private List<Waiter> waiters;

    private Meta meta;

    public WaitersResponse(List<Waiter> waiters, Meta meta) {
        this.waiters = waiters;
        this.meta = meta;
    }

    public WaitersResponse() {
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
