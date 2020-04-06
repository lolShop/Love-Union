package edu.nf.lol.adminUtil;

import java.io.Serializable;

public class AjaxPutPage implements Serializable {
    private Integer page;
    private Integer limit;
    private Integer start;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String toString(){
        return "AjaxPutPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" +start +
                '}';
    }
    }

