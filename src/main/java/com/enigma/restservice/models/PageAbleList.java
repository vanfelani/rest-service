package com.enigma.restservice.models;

import java.util.List;

public class PageAbleList<T> {

    private List<T> list;
    private int page;
    private int size;
    private Long total;

    public PageAbleList(List<T> list, int page, int size, Long total) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public int getpage() {
        return page;
    }

    public void setpage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
