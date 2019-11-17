package com.tsystems.javaschool.medical.backend.dto;

import java.util.List;

public class EventListResponse {
    private List<EventsDto> list;
    private int count;
    private long pages;
    private long records;

    public List<EventsDto> getList() {
        return list;
    }

    public void setList(List<EventsDto> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }
}
