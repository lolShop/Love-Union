package edu.nf.lol.product.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/9
 */
@Data
public class PageBean<T> {
    private Integer pageSize;
    private Integer pageTotal;
    private List<T> list;
    private Integer rows;

    public Integer getPageTotal() {
        this.pageTotal=rows % pageSize==0 ? rows/pageSize : (rows/pageSize+1);
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}