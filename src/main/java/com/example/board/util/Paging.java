package com.example.board.util;

public class Paging {
    int currentPage = 0;
    int totalCount = 0;
    int dataPerPage = 10;
    int lastPage = 0;

    int limit = 0;
    int offset = 0;

    public Paging(int currentPage, int totalCount) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.lastPage = this.totalCount / this.dataPerPage
                + (this.totalCount % this.dataPerPage > 0 ? 1 : 0);
        this.offset = calOffset();
        this.limit = this.dataPerPage;
    }

    public Paging(int currentPage, int totalCount, int dataPerPage) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.dataPerPage = dataPerPage;
        this.lastPage = this.totalCount / this.dataPerPage
                + (this.totalCount % this.dataPerPage > 0 ? 1 : 0);
        this.offset = calOffset();
        this.limit = this.dataPerPage;
    }

    int calOffset() {
        return (this.currentPage -1) * this.dataPerPage;
    }
    public static int parseNullInt(String pageNum) {
        if (pageNum == null || pageNum.isEmpty()) {
            return 1;
        } else {
            return Integer.parseInt(pageNum);
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getDataPerPage() {
        return dataPerPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", dataPerPage=" + dataPerPage +
                ", lastPage=" + lastPage +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
