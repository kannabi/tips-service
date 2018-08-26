package com.droptable.tipsservice.dao;

public class Response {
    private int status;
    private String description;

    public Response(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Response { status=" + status +
                ", description='" + description + "\' }";
    }
}
